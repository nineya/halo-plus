package com.nineya.haloplus.repository;

import java.util.Optional;

import com.nineya.haloplus.model.entity.Option;
import com.nineya.haloplus.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Option repository.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-20
 */
public interface OptionRepository
    extends BaseRepository<Option, Integer>, JpaSpecificationExecutor<Option> {

    /**
     * Query option by key
     *
     * @param key key
     * @return Option
     */
    Optional<Option> findByKey(String key);

    /**
     * Delete option by key
     *
     * @param key key
     */
    void deleteByKey(String key);
}
