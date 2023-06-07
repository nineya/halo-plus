package com.nineya.haloplus.repository;

import java.util.Optional;

import com.nineya.haloplus.model.entity.User;
import com.nineya.haloplus.repository.base.BaseRepository;
import org.springframework.lang.NonNull;

/**
 * User repository.
 *
 * @author johnniang
 */
public interface UserRepository extends BaseRepository<User, Integer> {

    /**
     * Gets user by username.
     *
     * @param username username must not be blank
     * @return an optional user
     */
    @NonNull
    Optional<User> findByUsername(@NonNull String username);

    /**
     * Gets user by email.
     *
     * @param email email must not be blank
     * @return an optional user
     */
    @NonNull
    Optional<User> findByEmail(@NonNull String email);
}
