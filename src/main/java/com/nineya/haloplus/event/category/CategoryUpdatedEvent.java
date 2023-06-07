package com.nineya.haloplus.event.category;

import java.util.Set;

import com.nineya.haloplus.model.entity.Category;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.Nullable;

/**
 * Category updated event.
 *
 * @author guqing
 * @date 2022-02-24
 */
public class CategoryUpdatedEvent extends ApplicationEvent {

    private final Category beforeUpdated;
    private final Category category;
    private final boolean beforeIsPrivate;
    private final Set<Integer> postIds;

    public CategoryUpdatedEvent(Object source, Category category,
        Category beforeUpdated, boolean beforeIsPrivate, Set<Integer> postIds) {
        super(source);
        this.category = category;
        this.beforeUpdated = beforeUpdated;
        this.beforeIsPrivate = beforeIsPrivate;
        this.postIds = postIds;
    }

    @Nullable
    public Category getCategory() {
        return category;
    }

    @Nullable
    public Category getBeforeUpdated() {
        return beforeUpdated;
    }

    public boolean isBeforeIsPrivate() {
        return beforeIsPrivate;
    }

    public Set<Integer> getPostIds() {
        return postIds;
    }
}
