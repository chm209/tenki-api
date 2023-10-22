package com.chm209.tenki.controller

import com.chm209.tenki.dto.tenkiDto
import com.chm209.tenki.service.TenkiService
import org.springframework.web.bind.annotation.*


@RestController
class TenkiController(private val tenkiService: TenkiService) {

    @RequestMapping("api/tenki", method = [RequestMethod.GET])
    @ResponseBody
    fun getTenki(@RequestParam(value = "region") region: String): tenkiDto {
        return tenkiService.getTenkiDto(region)
    }
}