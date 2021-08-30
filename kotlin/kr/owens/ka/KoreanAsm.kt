package kr.owens.ka

/**
 * @author owen151128@gmail.com
 *
 * Created by owen151128 on 2021/08/30 18:04
 *
 * Providing features related to KoreanAsm class
 */
object KoreanAsm {
    private const val KOR_BASE_CODE = 44032
    private const val CHO_SUNG_BASE = 588
    private const val CHO_SUNG_BASE_2 = 21
    private const val JUNG_SUNG_BASE = 28

    //  초성 리스트. 0 ~ 18
    private val CHO_SUNG_LIST = listOf(
        "ㄱ",
        "ㄲ",
        "ㄴ",
        "ㄷ",
        "ㄸ",
        "ㄹ",
        "ㅁ",
        "ㅂ",
        "ㅃ",
        "ㅅ",
        "ㅆ",
        "ㅇ",
        "ㅈ",
        "ㅉ",
        "ㅊ",
        "ㅋ",
        "ㅌ",
        "ㅍ",
        "ㅎ"
    )

    //  중성 리스트. 0 ~ 20
    private val JUNG_SUNG_LIST = listOf(
        "ㅏ",
        "ㅐ",
        "ㅑ",
        "ㅒ",
        "ㅓ",
        "ㅔ",
        "ㅕ",
        "ㅖ",
        "ㅗ",
        "ㅘ",
        "ㅙ",
        "ㅚ",
        "ㅛ",
        "ㅜ",
        "ㅝ",
        "ㅞ",
        "ㅟ",
        "ㅠ",
        "ㅡ",
        "ㅢ",
        "ㅣ"
    )

    //  종성 리스트. 0 ~ 27
    private val JONG_SUNG_LIST = listOf(
        " ",
        "ㄱ",
        "ㄲ",
        "ㄳ",
        "ㄴ",
        "ㄵ",
        "ㄶ",
        "ㄷ",
        "ㄹ",
        "ㄺ",
        "ㄻ",
        "ㄼ",
        "ㄽ",
        "ㄾ",
        "ㄿ",
        "ㅀ",
        "ㅁ",
        "ㅂ",
        "ㅄ",
        "ㅅ",
        "ㅆ",
        "ㅇ",
        "ㅈ",
        "ㅊ",
        "ㅋ",
        "ㅌ",
        "ㅍ",
        "ㅎ"
    )

    fun disAsm(text: String): List<String> {
        val result = mutableListOf<String>()

        for (s in text) {
            val code = s.code - KOR_BASE_CODE
            val choSungIndex = code / CHO_SUNG_BASE
            val jungSungIndex = (code - CHO_SUNG_BASE * choSungIndex) / JUNG_SUNG_BASE
            val jongSungIndex =
                (code - CHO_SUNG_BASE * choSungIndex - JUNG_SUNG_BASE * jungSungIndex)

            result.add(CHO_SUNG_LIST[choSungIndex])
            result.add(JUNG_SUNG_LIST[jungSungIndex])
            result.add(JONG_SUNG_LIST[jongSungIndex])
        }

        return result
    }

    fun asm(korList: List<String>): String {
        var result = ""
        var unicode = 0

        for (i in korList.indices) {
            when (i % 3) {
                0 -> unicode = CHO_SUNG_LIST.indexOf(korList[i]) * CHO_SUNG_BASE_2
                1 -> unicode = (unicode + JUNG_SUNG_LIST.indexOf(korList[i])) * JUNG_SUNG_BASE
                2 -> {
                    unicode += JONG_SUNG_LIST.indexOf(korList[i]) + KOR_BASE_CODE
                    result += unicode.toChar().toString()
                }
            }
        }

        return result
    }
}