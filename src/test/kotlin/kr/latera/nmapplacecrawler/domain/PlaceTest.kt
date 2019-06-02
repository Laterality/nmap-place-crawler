package kr.latera.nmapplacecrawler.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlaceTest {
    @Test
    fun create() {
        val name = "my place"
        val address = "some place, long street 12-3"
        val tel = "02-123-4567"
        val ref = "https://store.naver.com/restaurants/detail?id=32892073"
        assertThat(Place(name, address, tel, ref))
            .isEqualTo(Place(name, address, tel, ref))
    }
}