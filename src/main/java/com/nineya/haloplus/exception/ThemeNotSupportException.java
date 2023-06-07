package com.nineya.haloplus.exception;

/**
 * Theme not support exception.
 *
 * @author ryanwang
 * @date 2020-02-03
 */
public class ThemeNotSupportException extends BadRequestException {

    public ThemeNotSupportException(String message) {
        super(message);
    }

    public ThemeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
