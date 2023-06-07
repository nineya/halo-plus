package com.nineya.haloplus.listener.theme;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.nineya.haloplus.cache.AbstractStringCacheStore;
import com.nineya.haloplus.event.options.OptionUpdatedEvent;
import com.nineya.haloplus.event.theme.ThemeUpdatedEvent;
import com.nineya.haloplus.service.ThemeService;

/**
 * Theme updated listener.
 *
 * @author johnniang
 * @date 19-4-29
 */
@Component
public class ThemeUpdatedListener {

    private final AbstractStringCacheStore cacheStore;

    public ThemeUpdatedListener(AbstractStringCacheStore cacheStore) {
        this.cacheStore = cacheStore;
    }

    @EventListener
    public void onApplicationEvent(ThemeUpdatedEvent event) {
        cacheStore.delete(ThemeService.THEMES_CACHE_KEY);
    }

    @EventListener
    public void onOptionUpdatedEvent(OptionUpdatedEvent optionUpdatedEvent) {
        cacheStore.delete(ThemeService.THEMES_CACHE_KEY);
    }
}
