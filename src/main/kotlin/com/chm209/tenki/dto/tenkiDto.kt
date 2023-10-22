package com.chm209.tenki.dto

// @Builder :: kotlin은 빌더 패턴이 필요없다.
data class tenkiDto(
    val maximumTemp: Int,
    val minimumTemp: Int,
    val weather: String,
    val precipitation: Int)
{

}