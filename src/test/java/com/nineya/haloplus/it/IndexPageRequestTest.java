package com.nineya.haloplus.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

/**
 * Index page request test.
 *
 * @author johnniang
 */
@Slf4j
class IndexPageRequestTest extends BaseApiTest {

    @Test
    void indexPage() throws IOException {
        installBlog();
        // validate site link
        Document document = Jsoup.connect(blogUrl).get();
        Element siteLink = document.head().getElementsByAttributeValue("name", "site").get(0);
        assertEquals(blogUrl, siteLink.attr("content"));

        // validate post link
        Element postTitleLink =
            document.body().selectFirst(".container .card-content > .title > a");
        assertEquals("/archives/hello-halo", postTitleLink.attr("href"));
        assertEquals("Hello Halo", postTitleLink.text());
    }
}
