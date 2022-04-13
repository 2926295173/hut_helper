package com.wolfaonliu.cardreader

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

inline fun parserDomOfDouban(dom: Document, callBack: (Array<String>, Int) -> Unit) {
    /*  result[0] ： 中文名,
        result[1] :  原名,
        result[2] :  作者,
        result[3] :  译者,
        result[4] :  出版社,
        result[5] :  出版时间,
        result[6] :  定价,
        result[7] :  评分,
        result[8] :  评价人数,
        result[9] :  一句话简介,
 */
    val items = dom.select("tr.item td[valign=top]:nth-child(2)")
    items.mapIndexed { index, it ->
        val result = Array(10) { "" }

        val bookName = it.select("div.pl2")
        val cName = bookName.select("a").text()
        val fName = it.select("div.pl2 > span").text()
        result[0] = cName
        result[1] = fName

        val infos = it.select("p.pl")[0].text()
//        println(infos)
        val infosSplit = infos.split("/")
        when (infosSplit.size) {
            5 -> {
                result[2] = infosSplit[0]
                result[3] = infosSplit[1]
                result[4] = infosSplit[2]
                result[5] = infosSplit[3]
                result[6] = infosSplit[4]
            }
            4 -> {
                result[2] = infosSplit[0]
                result[4] = infosSplit[1]
                result[5] = infosSplit[2]
                result[6] = infosSplit[3]
            }
            6 -> {
                result[2] = infosSplit[0]
                result[3] = infosSplit[1]
                result[4] = infosSplit[2]
                result[5] = infosSplit[3]
                result[6] = infosSplit[4] + " ; " + infosSplit[5]
            }
        }
        result[7] = it.select("span.rating_nums").text()
        result[8] = it.select("div.star.clearfix span:nth-child(3)").text().substringBefore('人')
            .substring(2)

        result[9] = it.select("span.inq").text()
        callBack(result, index)
    }
}
//暂时 学习jsoup，没有使用,打算用
//implementation 'com.github.GateOfTruth:OkSimple:3.3.3'

fun entry() {

    for (i in 0..9) {
        val baseUrl = "https://book.douban.com/top250?start=${i * 25}"
        val dom = Jsoup.connect(baseUrl).get()
        parserDomOfDouban(dom) { data, index ->
            println(data)
            println(index + i * 25 + 1)
        }


    }

}
