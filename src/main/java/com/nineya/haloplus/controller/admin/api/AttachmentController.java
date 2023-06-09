package com.nineya.haloplus.controller.admin.api;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.nineya.haloplus.model.dto.AttachmentDTO;
import com.nineya.haloplus.model.dto.PhotoDTO;
import com.nineya.haloplus.model.entity.Attachment;
import com.nineya.haloplus.model.entity.Photo;
import com.nineya.haloplus.model.enums.AttachmentType;
import com.nineya.haloplus.model.params.AttachmentParam;
import com.nineya.haloplus.model.params.AttachmentQuery;
import com.nineya.haloplus.model.params.PhotoParam;
import com.nineya.haloplus.service.AttachmentService;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Attachment controller.
 *
 * @author johnniang
 * @date 2019-03-21
 */
@RestController
@RequestMapping("/api/admin/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping
    public Page<AttachmentDTO> pageBy(
        @PageableDefault(sort = "createTime", direction = DESC) Pageable pageable,
        AttachmentQuery attachmentQuery) {
        return attachmentService.pageDtosBy(pageable, attachmentQuery);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("Gets attachment detail by id")
    public AttachmentDTO getBy(@PathVariable("id") Integer id) {
        Attachment attachment = attachmentService.getById(id);
        return attachmentService.convertToDto(attachment);
    }

    @PutMapping("{attachmentId:\\d+}")
    @ApiOperation("Updates a attachment")
    public AttachmentDTO updateBy(@PathVariable("attachmentId") Integer attachmentId,
        @RequestBody @Valid AttachmentParam attachmentParam) {
        Attachment attachment = attachmentService.getById(attachmentId);
        attachmentParam.update(attachment);
        return new AttachmentDTO().convertFrom(attachmentService.update(attachment));
    }

    @PutMapping("/batch")
    @ApiOperation("Updates attachment in batch")
    public List<AttachmentDTO> updateBatchBy(@RequestBody List<@Valid AttachmentParam> attachmentParams) {
        List<Attachment> attachmentsToUpdate = attachmentParams.stream()
            .filter(attachmentParam -> Objects.nonNull(attachmentParam.getId()))
            .map(attachmentParam -> {
                Attachment attachmentToUpdate = attachmentService.getById(attachmentParam.getId());
                attachmentParam.update(attachmentToUpdate);
                return attachmentToUpdate;
            })
            .collect(Collectors.toList());
        return attachmentService.updateInBatch(attachmentsToUpdate).stream()
            .map(attachment -> (AttachmentDTO) new AttachmentDTO().convertFrom(attachment))
            .collect(Collectors.toList());
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("Deletes attachment permanently by id")
    public AttachmentDTO deletePermanently(@PathVariable("id") Integer id) {
        return attachmentService.convertToDto(attachmentService.removePermanently(id));
    }

    @DeleteMapping
    @ApiOperation("Deletes attachments permanently in batch by id array")
    public List<Attachment> deletePermanentlyInBatch(@RequestBody List<Integer> ids) {
        return attachmentService.removePermanently(ids);
    }

    @PostMapping("upload")
    @ApiOperation("Uploads single file")
    public AttachmentDTO uploadAttachment(@RequestPart("file") MultipartFile file) {
        return attachmentService.convertToDto(attachmentService.upload(file));
    }

    @PostMapping(value = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("Uploads multi files (Invalid in Swagger UI)")
    public List<AttachmentDTO> uploadAttachments(@RequestPart("files") MultipartFile[] files) {
        List<AttachmentDTO> result = new LinkedList<>();

        for (MultipartFile file : files) {
            // Upload single file
            Attachment attachment = attachmentService.upload(file);
            // Convert and add
            result.add(attachmentService.convertToDto(attachment));
        }

        return result;
    }

    @GetMapping("teams")
    @ApiOperation("Lists all of photo teams")
    public List<String> listTeams() {
        return attachmentService.listAllTeams();
    }

    @GetMapping("media_types")
    @ApiOperation("Lists all of media types")
    public List<String> listMediaTypes() {
        return attachmentService.listAllMediaType();
    }

    @GetMapping("types")
    @ApiOperation("Lists all of types.")
    public List<AttachmentType> listTypes() {
        return attachmentService.listAllType();
    }
}
