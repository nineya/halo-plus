package com.nineya.haloplus.service;

import com.nineya.haloplus.security.token.AuthToken;
import org.springframework.lang.NonNull;
import com.nineya.haloplus.model.dto.EnvironmentDTO;
import com.nineya.haloplus.model.dto.LoginPreCheckDTO;
import com.nineya.haloplus.model.entity.User;
import com.nineya.haloplus.model.params.LoginParam;
import com.nineya.haloplus.model.params.ResetPasswordParam;
import com.nineya.haloplus.model.params.ResetPasswordSendCodeParam;

/**
 * Admin service interface.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-04-29
 */
public interface AdminService {

    /**
     * Expired seconds.
     */
    int ACCESS_TOKEN_EXPIRED_SECONDS = 24 * 3600;

    int REFRESH_TOKEN_EXPIRED_DAYS = 30;

    String LOG_PATH = "logs/spring.log";

    /**
     * Authenticates username password.
     *
     * @param loginParam login param must not be null
     * @return User
     */
    @NonNull
    User authenticate(@NonNull LoginParam loginParam);

    /**
     * Check authCode and build authToken.
     *
     * @param loginParam login param must not be null
     * @return User
     */
    @NonNull
    AuthToken authCodeCheck(@NonNull LoginParam loginParam);

    /**
     * Clears authentication.
     */
    void clearToken();

    /**
     * Send reset password code to administrator's email.
     *
     * @param param param must not be null
     */
    void sendResetPasswordCode(@NonNull ResetPasswordSendCodeParam param);

    /**
     * Reset password by code.
     *
     * @param param param must not be null
     */
    void resetPasswordByCode(@NonNull ResetPasswordParam param);

    /**
     * Get system environments
     *
     * @return environments
     */
    @NonNull
    EnvironmentDTO getEnvironments();

    /**
     * Refreshes token.
     *
     * @param refreshToken refresh token must not be blank
     * @return authentication token
     */
    @NonNull
    AuthToken refreshToken(@NonNull String refreshToken);

    /**
     * Get halo logs content.
     *
     * @param lines lines
     * @return logs content.
     */
    String getLogFiles(@NonNull Long lines);

    /**
     * Get user login env
     *
     * @param username username must not be null
     * @return LoginEnvDTO
     */
    LoginPreCheckDTO getUserEnv(@NonNull String username);
}
