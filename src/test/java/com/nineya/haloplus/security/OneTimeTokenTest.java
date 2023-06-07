package com.nineya.haloplus.security;

import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.nineya.haloplus.cache.AbstractStringCacheStore;
import com.nineya.haloplus.model.properties.PrimaryProperties;
import com.nineya.haloplus.security.service.OneTimeTokenService;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class OneTimeTokenTest {

    static final String REQUEST_URI = "/api/admin/statistics";

    @Autowired
    MockMvc mvc;

    @Autowired
    OneTimeTokenService oneTimeTokenService;

    @Autowired
    AbstractStringCacheStore cacheStore;

    Map<String, Object> map = new HashMap<>();

    {
        map.put(PrimaryProperties.BIRTHDAY.getValue(), String.valueOf(DateUtils.now().getTime()));
    }

    @BeforeEach
    void setUp() {
        cacheStore.putAny(OptionService.OPTIONS_KEY, map);
    }

    @Test
    void provideNonExistOneTimeTokenTest() throws Exception {
        mvc.perform(get(REQUEST_URI + "?ott={ott}", "one-time-token-value"))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @Test
    void insertAnOneTimeTokenTest() throws Exception {
        // Create ott
        String ott = oneTimeTokenService.create(REQUEST_URI);

        mvc.perform(get(REQUEST_URI + "?ott={ott}", ott))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
