package com.nineya.haloplus.security.authentication;

import org.springframework.lang.NonNull;
import com.nineya.haloplus.security.support.UserDetail;

/**
 * Authentication.
 *
 * @author johnniang
 */
public interface Authentication {

    /**
     * Get user detail.
     *
     * @return user detail
     */
    @NonNull
    UserDetail getDetail();
}
