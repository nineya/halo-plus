package com.nineya.haloplus.listener;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import com.nineya.haloplus.event.category.CategoryUpdatedEvent;
import com.nineya.haloplus.model.entity.Category;
import com.nineya.haloplus.model.entity.Post;
import com.nineya.haloplus.model.enums.PostStatus;
import com.nineya.haloplus.model.vo.PostDetailVO;
import com.nineya.haloplus.service.CategoryService;
import com.nineya.haloplus.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

/**
 * @author guqing
 * @date 2022-03-28
 */
@SpringBootTest
@RecordApplicationEvents
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRefreshStatusListenerTest {

    @Autowired
    private ApplicationEvents applicationEvents;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    private Post post;
    private Category category1;

    @BeforeEach
    public void setUp() {
        category1 = new Category();
        category1.setId(1);
        category1.setName("category-1");
        category1.setSlug("category-1");
        category1.setPassword("123");
        category1.setParentId(0);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("category-2");
        category2.setSlug("category-2");
        category2.setParentId(1);

        categoryService.create(category1);
        categoryService.create(category2);

        post = new Post();
        post.setId(1);
        post.setSlug("post-1");
        post.setTitle("post-title");
    }

    @Test
    public void clearCategoryPasswordOfTopLevelTest() {
        // After clearing the password of the top-level category,
        // all articles belonging to this category and sub categories should be set
        // to draft status.
        PostDetailVO postDetailVO =
            postService.createBy(post, Set.of(), Set.of(2), Set.of(), false);
        assertThat(postDetailVO).isNotNull();

        category1.setPassword(null);
        categoryService.update(category1);
        assertThat(applicationEvents
            .stream(CategoryUpdatedEvent.class)
            .filter(event -> event.getCategory().getId() == 1)
            .count())
            .isEqualTo(1);

        Post updatedPost = postService.getById(postDetailVO.getId());
        assertThat(updatedPost).isNotNull();
        Assertions.assertThat(updatedPost.getStatus()).isEqualTo(PostStatus.DRAFT);
    }
}
