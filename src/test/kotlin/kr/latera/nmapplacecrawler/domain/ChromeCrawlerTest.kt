package kr.latera.nmapplacecrawler.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChromeCrawlerTest {

    /**
     * IMPORTANT: This test can fail in the future as the search result is out of the control of this application
     */
    @Test
    fun crawlWithKeyword() {
        val results = ChromeCrawler("webdriver/chromedriver_74_win32.exe", true).crawl("우아한형제들")
        assertThat(results).hasSize(4)
            .containsExactly(
                Place("우아한형제들", "서울특별시 송파구 위례성대로 2 장은빌딩", "1600-7001", "https://map.naver.com/local/siteview.nhn?code=21111457"),
                Place("우아한형제들 작은집", "서울특별시 송파구 올림픽로 295", "1577-6837", "https://map.naver.com/local/siteview.nhn?code=1896235703"),
                Place("우아한형제들", "부산광역시 북구 금곡대로 135", "", "https://map.naver.com/local/siteview.nhn?code=1574112390"),
                Place("배민아카데미", "서울특별시 송파구 위례성대로 6 현대토픽스빌딩 3층", "1600-0987", "https://map.naver.com/local/siteview.nhn?code=225659059")
            )
    }
}