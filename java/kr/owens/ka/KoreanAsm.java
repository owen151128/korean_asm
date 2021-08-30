package kr.owens.ka;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2021/08/30 17:20
 * <p>
 * Providing features related to KoreanAsm class
 */
public class KoreanAsm {

  private static final int KOR_BASE_CODE = 44032;
  private static final int CHO_SUNG_BASE = 588;
  private static final int CHO_SUNG_BASE_2 = 21;
  private static final int JUNG_SUNG_BASE = 28;

  //  초성 리스트. 0 ~ 18
  private static final String[] CHO_SUNG_LIST = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
      "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

  //  중성 리스트. 0 ~ 20
  private static final String[] JUNG_SUNG_LIST = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
      "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ",
      "ㅣ"};

  //  종성 리스트. 0 ~ 27
  private static final String[] JONG_SUNG_LIST = {" ", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ",
      "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ",
      "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

  public static List<String> disAsm(String text) {
    List<String> result = new ArrayList<>();
    int code, choSungIndex, jungSungIndex, jongSungIndex;

    for (char i : text.toCharArray()) {
      code = i - KOR_BASE_CODE;
      choSungIndex = code / CHO_SUNG_BASE;
      jungSungIndex = (code - CHO_SUNG_BASE * choSungIndex) / JUNG_SUNG_BASE;
      jongSungIndex = code - CHO_SUNG_BASE * choSungIndex - JUNG_SUNG_BASE * jungSungIndex;

      result.add(CHO_SUNG_LIST[choSungIndex]);
      result.add(JUNG_SUNG_LIST[jungSungIndex]);
      result.add(JONG_SUNG_LIST[jongSungIndex]);
    }

    return result;
  }

  public static String asm(List<String> korList) {
    StringBuilder sb = new StringBuilder();
    int unicode = 0;
    OptionalInt streamIndex;

    for (int i = 0; i < korList.size(); i++) {
      final int index = i;
      switch (i % 3) {
        case 0:
          streamIndex = IntStream.range(0, CHO_SUNG_LIST.length)
              .filter(value -> CHO_SUNG_LIST[value].equals(korList.get(index))).findFirst();
          if (streamIndex.isPresent()) {
            unicode = streamIndex.getAsInt() * CHO_SUNG_BASE_2;
          }
          break;
        case 1:
          streamIndex = IntStream.range(0, JUNG_SUNG_LIST.length)
              .filter(value -> JUNG_SUNG_LIST[value].equals(korList.get(index))).findFirst();
          if (streamIndex.isPresent()) {
            unicode = (unicode + streamIndex.getAsInt()) * JUNG_SUNG_BASE;
          }
          break;
        default:
          streamIndex = IntStream.range(0, JONG_SUNG_LIST.length)
              .filter(value -> JONG_SUNG_LIST[value].equals(korList.get(index))).findFirst();
          if (streamIndex.isPresent()) {
            unicode += streamIndex.getAsInt() + KOR_BASE_CODE;
            sb.append((char) unicode);
          }
      }
    }

    return sb.toString().intern();
  }
}
