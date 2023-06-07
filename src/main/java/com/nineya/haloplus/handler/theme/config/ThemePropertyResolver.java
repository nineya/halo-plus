package com.nineya.haloplus.handler.theme.config;

import java.io.IOException;

import com.nineya.haloplus.handler.theme.config.support.ThemeProperty;
import org.springframework.lang.NonNull;

/**
 * Theme file resolver.
 *
 * @author johnniang
 * @date 2019-04-11
 */
public interface ThemePropertyResolver {

    /**
     * Resolves the theme file.
     *
     * @param content file content must not be null
     * @return theme file
     */
    @NonNull
    ThemeProperty resolve(@NonNull String content) throws IOException;
}
