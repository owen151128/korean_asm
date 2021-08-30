import kr.owens.ka.KoreanAsm

/**
 * @author owen151128@gmail.com
 *
 * Created by owen151128 on 2021/08/30 17:20
 *
 * Providing features related to Main class
 */
fun main() {
    val koreanInput = "안녕하세요오웬입니다"
    val result = KoreanAsm.disAsm(koreanInput)
    println("dis_asm result : $result")
    println("asm result : ${KoreanAsm.asm(result)}")
}