package com.nineya.haloplus.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nineya.haloplus.utils.MarkdownUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

// CS304 issue link : https://github.com/halo-dev/halo/issues/1224

@Slf4j
public class HTMLWordCountTest {

    String markdownWithPicture = "图片字数测试\n"
        + "![image.png](http://127.0.0.1:8090/upload/2021/04/image-51142fdc369c48698dd75c24f6049738"
        + ".png)";

    String markdownWithTitle = "# 标题字数测试";

    String markdownWithFontType = "++~~***字体样式字数测试***~~++";

    String markdownWithCodeType = "`代码样式字数测试`";

    String markdownWithLink = "[链接字数测试](https://www.baidu.com)";

    String markdownWithTable = "|表格|字数|测试|\n"
        + "|-------|-------|-------|\n"
        + "|表格|字数|测试|\n";

    String plainText = "纯文本字数测试";

    String complexText = "# 复杂文本测试\n\n"
        + "![图片不算字数](http://127.0.0.1:8090/upload/2021/04/image-51142fdc369c48698dd75c24f6049738)"
        + "\n\n"
        + "++~~***复杂文本测试***~~++  `复杂文本测试`  [复杂文本测试](https://halo.run)\n\n"
        + "|复杂|文本|测试|\n"
        + "|-------|-------|-------|\n"
        + "|复杂|文本|测试|\n\n"
        + "## 复杂文本测试\n";

    String htmlText =
        "<body><h1>复杂文本测试 </h1>\n"
            + "<p><img src=\"http://127.0.0"
            + ".1:8090/upload/2021/04/image-51142fdc369c48698dd75c24f6049738\" "
            + "referrerpolicy=\"no-referrer\" alt=\"图片不算字数\"> \n"
            + "<del><strong><em>复杂文本测试</em></strong></del>  <code>复杂文本测试</code>  <a "
            + "href='https://halo"
            + ".run'>复杂文本测试</a> </p>\n"
            + "<figure><table>\n"
            + "<thead>\n"
            + "<tr><th>复杂</th><th>文本</th><th>测试</th></tr></thead>\n"
            + "<tbody><tr><td>复杂</td><td>文本</td><td>测试</td></tr></tbody>\n"
            + "</table></figure>\n"
            + "<h2>复杂文本测试</h2>\n"
            + "</body>\n"
            + "</html>";

    String nullString = null;

    String emptyString = "";

    String englishString = "I have a red apple";

    String hybridString = "I have a red apple哈哈";


    String complexText2 = "Hi，Jessica！这个project的schedule有些问题。";

    String complexText3 = "The company had a meeting yesterday。Why did you ask for leave？";

    String complexText4 = "这是一个句子，但是只有中文。";

    String complexText5 =
        "The wind and the moon are all beautiful, love and hate are all romantic.";

    @Test
    void pictureTest() {
        assertEquals("图片字数测试".length(),
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(markdownWithPicture)));
    }

    @Test
    void titleTest() {
        assertEquals("标题字数测试".length(),
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(markdownWithTitle)));
    }

    @Test
    void fontTypeTest() {
        assertEquals("字体样式字数测试".length(),
            BasePostServiceImpl
                .htmlFormatWordCount(MarkdownUtils.renderHtml(markdownWithFontType)));
    }

    @Test
    void codeTypeTest() {
        assertEquals("代码样式字数测试".length(),
            BasePostServiceImpl
                .htmlFormatWordCount(MarkdownUtils.renderHtml(markdownWithCodeType)));
    }

    @Test
    void linkTest() {
        assertEquals("链接字数测试".length(),
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(markdownWithLink)));
    }

    @Test
    void tableTest() {
        assertEquals("表格字数测试".length() * 2,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(markdownWithTable)));
    }

    @Test
    void plainTextTest() {
        assertEquals(plainText.length(),
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(plainText)));
    }

    @Test
    void complexTextTest() {
        assertEquals("复杂文本测试复杂文本测试复杂文本测试复杂文本测试复杂文本测试复杂文本测试复杂文本测试".length(),
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(complexText)));
    }

    @Test
    void htmlTest() {
        assertEquals("复杂文本测试复杂文本测试复杂文本测试复杂文本测试复杂文本测试复杂文本测试复杂文本测试".length(),
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(htmlText)));
    }

    @Test
    void nullTest() {
        assertEquals(0,
            BasePostServiceImpl.htmlFormatWordCount(null));
        assertEquals(0,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(nullString)));
    }

    @Test
    void emptyTest() {
        assertEquals(0,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(emptyString)));
    }

    @Test
    void englishTest() {
        assertEquals(5,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(englishString)));
    }

    @Test
    void hybridTest() {
        assertEquals(7,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(hybridString)));
    }

    @Test
    void englishCharacterTest() {
        assertEquals(14,
            BasePostServiceImpl.htmlFormatCharacterCount(MarkdownUtils.renderHtml(englishString)));
    }

    @Test
    void hybridCharacterTest() {
        assertEquals(16,
            BasePostServiceImpl.htmlFormatCharacterCount(MarkdownUtils.renderHtml(hybridString)));
    }

    @Test
    void moreComplexTest() {
        assertEquals(14,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(complexText2)));
        assertEquals(14,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(complexText3)));
        assertEquals(14,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(complexText4)));
        assertEquals(14,
            BasePostServiceImpl.htmlFormatWordCount(MarkdownUtils.renderHtml(complexText5)));
    }


}