package com.nineya.haloplus.repository;

import java.util.Optional;

import com.nineya.haloplus.model.entity.CommentBlackList;
import com.nineya.haloplus.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 评论黑名单Repository
 *
 * @author Lei XinXin
 * @date 2020/1/3
 */
public interface CommentBlackListRepository extends BaseRepository<CommentBlackList, Long> {

    /**
     * 根据IP地址获取数据.
     *
     * @param ipAddress ip address
     * @return comment black list or empty
     */
    Optional<CommentBlackList> findByIpAddress(String ipAddress);

    /**
     * Update Comment BlackList By IPAddress.
     *
     * @param commentBlackList comment black list
     * @return result
     */
    @Modifying
    @Query("UPDATE CommentBlackList SET banTime=:#{#commentBlackList.banTime} WHERE "
        + "ipAddress=:#{#commentBlackList.ipAddress}")
    int updateByIpAddress(@Param("commentBlackList") CommentBlackList commentBlackList);
}
