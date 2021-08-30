import java.util.List;
import kr.owens.ka.KoreanAsm;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2021/08/30 17:20
 * <p>
 * Providing features related to Main class
 */
public class Main {

  public static void main(String[] args) {
    String korean_input = "안녕하세요오웬입니다";
    List<String> result = KoreanAsm.disAsm(korean_input);
    System.out.println("dis_asm result : " + result);
    System.out.println("asm result : " + KoreanAsm.asm(result));
  }
}
