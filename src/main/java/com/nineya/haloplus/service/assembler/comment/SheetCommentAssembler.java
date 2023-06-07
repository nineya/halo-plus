package com.nineya.haloplus.service.assembler.comment;

import static com.nineya.haloplus.model.support.HaloConst.URL_SEPARATOR;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.nineya.haloplus.model.dto.post.BasePostMinimalDTO;
import com.nineya.haloplus.model.entity.Sheet;
import com.nineya.haloplus.model.entity.SheetComment;
import com.nineya.haloplus.model.enums.SheetPermalinkType;
import com.nineya.haloplus.model.vo.SheetCommentWithSheetVO;
import com.nineya.haloplus.repository.SheetRepository;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.utils.ServiceUtils;

/**
 * Sheet comment assembler.
 *
 * @author guqing
 * @date 2022-03-08
 */
@Component
public class SheetCommentAssembler extends BaseCommentAssembler<SheetComment> {

    private final SheetRepository sheetRepository;

    private final OptionService optionService;

    public SheetCommentAssembler(OptionService optionService,
        SheetRepository sheetRepository) {
        super(optionService);
        this.sheetRepository = sheetRepository;
        this.optionService = optionService;
    }

    /**
     * Converts to with sheet vo
     *
     * @param comment comment
     * @return a comment with sheet vo
     */
    @NonNull
    public SheetCommentWithSheetVO convertToWithSheetVo(@NonNull SheetComment comment) {
        Assert.notNull(comment, "SheetComment must not be null");
        SheetCommentWithSheetVO sheetCommentWithSheetVo =
            new SheetCommentWithSheetVO().convertFrom(comment);

        BasePostMinimalDTO basePostMinimalDto =
            new BasePostMinimalDTO().convertFrom(sheetRepository.getOne(comment.getPostId()));

        sheetCommentWithSheetVo.setSheet(buildSheetFullPath(basePostMinimalDto));

        sheetCommentWithSheetVo.setAvatar(buildAvatarUrl(comment.getGravatarMd5()));

        return sheetCommentWithSheetVo;
    }

    /**
     * Converts to with sheet vo
     *
     * @param sheetComments sheet comments
     * @return a sheet comments with sheet vo
     */
    @NonNull
    public List<SheetCommentWithSheetVO> convertToWithSheetVo(List<SheetComment> sheetComments) {
        if (CollectionUtils.isEmpty(sheetComments)) {
            return Collections.emptyList();
        }

        Set<Integer> sheetIds = ServiceUtils.fetchProperty(sheetComments, SheetComment::getPostId);

        Map<Integer, Sheet> sheetMap =
            ServiceUtils.convertToMap(sheetRepository.findAllById(sheetIds), Sheet::getId);

        return sheetComments.stream()
            .filter(comment -> sheetMap.containsKey(comment.getPostId()))
            .map(comment -> {
                SheetCommentWithSheetVO sheetCmtWithPostVo =
                    new SheetCommentWithSheetVO().convertFrom(comment);

                BasePostMinimalDTO postMinimalDto =
                    new BasePostMinimalDTO().convertFrom(sheetMap.get(comment.getPostId()));

                sheetCmtWithPostVo.setSheet(buildSheetFullPath(postMinimalDto));

                sheetCmtWithPostVo.setAvatar(buildAvatarUrl(comment.getGravatarMd5()));

                return sheetCmtWithPostVo;
            })
            .collect(Collectors.toList());
    }

    /**
     * Converts to with sheet vo
     *
     * @param sheetCommentPage sheet comments
     * @return a page of sheet comments with sheet vo
     */
    @NonNull
    public Page<SheetCommentWithSheetVO> convertToWithSheetVo(
        @NonNull Page<SheetComment> sheetCommentPage) {
        Assert.notNull(sheetCommentPage, "Sheet comment page must not be null");

        return new PageImpl<>(convertToWithSheetVo(sheetCommentPage.getContent()),
            sheetCommentPage.getPageable(), sheetCommentPage.getTotalElements());
    }

    private BasePostMinimalDTO buildSheetFullPath(BasePostMinimalDTO basePostMinimalDto) {
        StringBuilder fullPath = new StringBuilder();

        SheetPermalinkType permalinkType = optionService.getSheetPermalinkType();

        if (optionService.isEnabledAbsolutePath()) {
            fullPath.append(optionService.getBlogBaseUrl());
        }

        if (permalinkType.equals(SheetPermalinkType.SECONDARY)) {
            fullPath.append(URL_SEPARATOR)
                .append(optionService.getSheetPrefix())
                .append(URL_SEPARATOR)
                .append(basePostMinimalDto.getSlug())
                .append(optionService.getPathSuffix());
        } else if (permalinkType.equals(SheetPermalinkType.ROOT)) {
            fullPath.append(URL_SEPARATOR)
                .append(basePostMinimalDto.getSlug())
                .append(optionService.getPathSuffix());
        }

        basePostMinimalDto.setFullPath(fullPath.toString());
        return basePostMinimalDto;
    }
}
