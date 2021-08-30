# -*- coding: utf-8 -*-

KOR_BASE_CODE, CHO_SUNG_BASE, CHO_SUNG_BASE_2, JUNG_SUNG_BASE = 44032, 588, 21, 28

# 초성 리스트. 0 ~ 18
CHO_SUNG_LIST = ['ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ']

# 중성 리스트. 0 ~ 20
JUNG_SUNG_LIST = ['ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ',
                  'ㅣ']

# 종성 리스트. 0 ~ 27
JONG_SUNG_LIST = [' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ',
                  'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ']


def dis_asm(text: str) -> list:
    result = []

    for i in text:
        code = ord(i) - KOR_BASE_CODE
        cho_sung_index = int(code / CHO_SUNG_BASE)
        result += CHO_SUNG_LIST[cho_sung_index]

        jung_sung_index = int((code - (CHO_SUNG_BASE * cho_sung_index)) / JUNG_SUNG_BASE)
        result += JUNG_SUNG_LIST[jung_sung_index]

        jong_sung_index = int((code - (CHO_SUNG_BASE * cho_sung_index) - (JUNG_SUNG_BASE * jung_sung_index)))
        result += JONG_SUNG_LIST[jong_sung_index]

    return result


def asm(kor_list: list) -> str:
    result = []
    unicode = 0
    for i, v in enumerate(kor_list):
        order = i % 3
        if order == 0:
            unicode = CHO_SUNG_LIST.index(kor_list[i]) * CHO_SUNG_BASE_2
        elif order == 1:
            unicode = (unicode + JUNG_SUNG_LIST.index(kor_list[i])) * JUNG_SUNG_BASE
        else:
            unicode = unicode + JONG_SUNG_LIST.index(kor_list[i]) + KOR_BASE_CODE
            result += chr(unicode)

    return ''.join(result)


def main():
    korean_input = '안녕하세요오웬입니다'
    result = dis_asm(korean_input)
    print(f'dis_asm result : {result}')
    print(f'asm result : {asm(result)}')


if __name__ == '__main__':
    main()
