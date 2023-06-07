package com.nineya.haloplus.security.service.impl;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.nineya.haloplus.cache.AbstractStringCacheStore;
import com.nineya.haloplus.security.service.OneTimeTokenService;
import com.nineya.haloplus.utils.HaloUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * One-time token service implementation.
 *
 * @author johnniang
 */
@Service
public class OneTimeTokenServiceImpl implements OneTimeTokenService {

    private static final String tokenPrefix = "OTT-";

    private static final Duration OTT_EXPIRATION_TIME = Duration.ofMinutes(5);

    private final AbstractStringCacheStore cacheStore;

    public OneTimeTokenServiceImpl(AbstractStringCacheStore cacheStore) {
        this.cacheStore = cacheStore;
    }

    @Override
    public Optional<String> get(String oneTimeToken) {
        Assert.hasText(oneTimeToken, "One-time token must not be blank");

        // Get from cache store
        return cacheStore.get(tokenPrefix + oneTimeToken);
    }

    @Override
    public String create(String uri) {
        Assert.hasText(uri, "Request uri must not be blank");

        // Generate ott
        String oneTimeToken = HaloUtils.randomUUIDWithoutDash();

        // Put ott along with request uri
        cacheStore.put(tokenPrefix + oneTimeToken,
            uri,
            OTT_EXPIRATION_TIME.getSeconds(),
            TimeUnit.SECONDS);

        // Return ott
        return oneTimeToken;
    }

    @Override
    public void revoke(String oneTimeToken) {
        Assert.hasText(oneTimeToken, "One-time token must not be blank");

        // Delete the token
        cacheStore.delete(tokenPrefix + oneTimeToken);
    }
}
