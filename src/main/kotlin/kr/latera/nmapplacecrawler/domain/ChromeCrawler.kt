package kr.latera.nmapplacecrawler.domain

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.IllegalStateException

class ChromeCrawler(
    webDriverPath: String,
    headless: Boolean) {

    private val wd: ChromeDriver

    init {
        System.setProperty("webdriver.chrome.driver", webDriverPath)
        val options = ChromeOptions()
        if (headless) {
            options.addArguments("--headless")
        }

        wd = ChromeDriver(options)
    }

    fun crawl(keyword: String): List<Place> {
        wd.get("https://map.naver.com/")
        val searchBtn = wd.findElementByCssSelector("button.spm")
        wd.findElementByCssSelector("input#search-input.input_act._search_input").sendKeys(keyword)
        searchBtn.click()
        WebDriverWait(wd, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.lst_site")))
        val resultItems = wd.findElementsByCssSelector("ul.lst_site > li")
        val results = ArrayList<Place>()
        resultItems.forEach {i ->
            results.add(Place(
                getPlaceName(i),
                getPlaceAddress(i),
                getPlaceTel(i),
                getPlaceRefURL(i)
            ))
        }

        return results
    }

    private fun getPlaceName(element: WebElement): String {
        try {
            return element.findElement(By.cssSelector("dt > a")).text
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            throw IllegalStateException("Element has no place name")
        }
    }

    private fun getPlaceAddress(element: WebElement): String {
        try {
            return removeExtraText(element.findElement(By.cssSelector("dd.addr")).text)
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            return ""
        }
    }

    private fun removeExtraText(str: String): String {
        if (str.endsWith("지번")) {
            return str.substring(0, str.length - 2).trim()
        }
        return str
    }

    private fun getPlaceTel(element: WebElement): String {
        try {
            return element.findElement(By.cssSelector("dd.tel")).text
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            return ""
        }
    }

    private fun getPlaceRefURL(element: WebElement) = createRefURL(element.getAttribute("data-id"))

    private fun createRefURL(id: String): String {
        return "https://map.naver.com/local/siteview.nhn?code=${id.substring(1)}"
    }
}
