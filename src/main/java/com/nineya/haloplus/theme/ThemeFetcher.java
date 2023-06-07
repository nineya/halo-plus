package com.nineya.haloplus.theme;

import com.nineya.haloplus.handler.theme.config.support.ThemeProperty;

/**
 * Remote theme fetcher interface.
 *
 * @author johnniang
 */
public interface ThemeFetcher {

    /**
     * Check whether source is supported or not.
     *
     * @param source input stream or remote git uri
     * @return true if supported, false otherwise
     */
    boolean support(Object source);

    /**
     * Fetch theme from source.
     *
     * @param source input stream or remote git uri
     * @return theme property
     */
    ThemeProperty fetch(Object source);

}
