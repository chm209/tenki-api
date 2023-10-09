import com.google.gson.Gson
import org.jsoup.Jsoup
import java.io.FileWriter
import java.io.PrintWriter

fun main() {
    val targetA = "a#forecast-map-entry-"
    val targetList = listOf(" > span.max-temp", " > span.min-temp", " > img.forecast-image", " > span.prob-precip")
    val regionNameList = listOf("규슈", "시코쿠", "주고쿠", "간사이", "주부", "간토", "도호쿠", "홋카이도", "오키나와")
    val regionIndexList = listOf("40130", "39201", "34100", "27100", "23100", "13101", "4100", "1100", "47201")
    val exportJsonPath = "tenki/currentTenki.json"
    val docs = Jsoup.connect("https://tenki.jp/").get()
    var tenkiArray = Array(9){Array(5){ "" } }

    for( index in 1..9) {
        tenkiArray[index-1][0] = regionNameList[index-1]
        tenkiArray[index-1][1] = docs.select(targetA + regionIndexList[index-1] + targetList[0]).toString().replace(("[^\\d.]").toRegex(), "")
        tenkiArray[index-1][2] = docs.select(targetA + regionIndexList[index-1] + targetList[1]).toString().replace(("[^\\d.]").toRegex(), "")
        tenkiArray[index-1][3] = docs.select(targetA + regionIndexList[index-1] + targetList[2]).attr("alt").toString()
        tenkiArray[index-1][4] = docs.select(targetA + regionIndexList[index-1] + targetList[3]).toString().replace(("[^\\d.]").toRegex(), "")
    }

     try {
        PrintWriter(FileWriter(exportJsonPath)).use {
            val gson = Gson()
            val jsonString = gson.toJson(tenkiArray)
            it.write(jsonString)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
