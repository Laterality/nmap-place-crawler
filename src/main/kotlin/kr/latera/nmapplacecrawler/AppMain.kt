package kr.latera.nmapplacecrawler

import kr.latera.nmapplacecrawler.domain.ChromeCrawler

fun main() {
    val results = ChromeCrawler("webdriver/chromedriver_74_win32.exe", true).crawl("잠실역 술집")
    println(results)
}