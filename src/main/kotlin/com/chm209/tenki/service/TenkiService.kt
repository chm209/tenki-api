package com.chm209.tenki.service

import com.chm209.tenki.dto.tenkiDto
import org.jsoup.Jsoup
import org.springframework.stereotype.Service

@Service
class TenkiService {
    val targetA = "a#forecast-map-entry-"
    val targetList = listOf(" > span.max-temp", " > span.min-temp", " > img.forecast-image", " > span.prob-precip")
    val docs = Jsoup.connect("https://tenki.jp/").get()

    fun getTenkiDto(region: String): tenkiDto {
        val regionIndex = setRegionIndex(region)

        return tenkiDto(
            (docs.select(targetA + regionIndex + targetList[0]).toString().replace(("[^\\d.]").toRegex(), "")).toInt(),
            (docs.select(targetA + regionIndex + targetList[1]).toString().replace(("[^\\d.]").toRegex(), "")).toInt(),
            docs.select(targetA + regionIndex + targetList[2]).attr("alt"),
            (docs.select(targetA + regionIndex + targetList[3]).toString().replace(("[^\\d.]").toRegex(), "")).toInt()
        )
    }

    // 지역 인덱스 번호 설정
    fun setRegionIndex(region: String): String {
        when (region) {
            "10" -> return "40130"
            "20" -> return "39201"
            "30" -> return "34100"
            "40" -> return "27100"
            "50" -> return "23100"
            "60" -> return "13101"
            "70" -> return "4100"
            "80" -> return "1100"
            "90" -> return "47201"
        }
        return "40130"
    }
}

