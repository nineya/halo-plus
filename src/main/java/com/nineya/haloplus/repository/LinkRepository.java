package com.nineya.haloplus.repository;

import java.util.List;

import com.nineya.haloplus.model.entity.Link;
import com.nineya.haloplus.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Link repository.
 *
 * @author johnniang
 */
public interface LinkRepository extends BaseRepository<Link, Integer> {

    /**
     * Find all link teams.
     *
     * @return a list of teams
     */
    @Query(value = "select a.team from Link a group by a.team order by max(a.priority) DESC")
    List<String> findAllTeams();

    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByUrlAndIdNot(String url, Integer id);
}
