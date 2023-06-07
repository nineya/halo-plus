package com.nineya.haloplus.service;

import com.nineya.haloplus.model.dto.StatisticDTO;
import com.nineya.haloplus.model.dto.StatisticWithUserDTO;

/**
 * Statistic service interface.
 *
 * @author ryanwang
 * @date 2019-12-16
 */
public interface StatisticService {

    /**
     * Get blog statistic.
     *
     * @return statistic dto.
     */
    StatisticDTO getStatistic();

    /**
     * Get blog statistic with user info.
     *
     * @return statistic with user info dto.
     */
    StatisticWithUserDTO getStatisticWithUser();
}
