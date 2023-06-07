package com.nineya.haloplus.handler.theme.config.impl;

import java.io.IOException;

import com.nineya.haloplus.handler.theme.config.support.ThemeProperty;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.nineya.haloplus.handler.theme.config.ThemePropertyResolver;
import com.nineya.haloplus.theme.YamlResolver;

/**
 * Yaml theme file resolver.
 *
 * @author johnniang
 * @date 2019-04-11
 */
@Service
public class YamlThemePropertyResolver implements ThemePropertyResolver {

    @Override
    @NonNull
    public ThemeProperty resolve(@NonNull String content) throws IOException {
        Assert.hasText(content, "Theme file content must not be null");

        return YamlResolver.INSTANCE.getYamlMapper().readValue(content, ThemeProperty.class);
    }
}
