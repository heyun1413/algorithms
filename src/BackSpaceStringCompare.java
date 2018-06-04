import java.util.ArrayList;
import java.util.List;

/**
 * @author ron
 * backspace string compare
 * 给定两个串  S 并T，返回，如果他们是平等的，当两者都输入到空的文本编辑器。#意味着退格字符。
 */
public class BackSpaceStringCompare {

    private static final char BACKSPACE_CHARACTER = '#';

    public static void main(String[] args) {
        //S = “ab＃c”，T = “ad＃c”
        assert compare("ab#c", "ad#c");
        assert compareWithReverse("ab#c", "ad#c");

        //S = “ab ##”，T = “c＃d＃”
        assert compare("ab##", "c#d#");
        assert compareWithReverse("ab##", "c#d#");


        //S = “a ## c”，T = “＃a＃c”
        assert compare("a##c", "#a#c");
        assert compareWithReverse("a##c", "#a#c");
    }

    /**
     * 使用栈结构进行比较
     * @param a
     * @param b
     * @return
     */
    private static boolean compare(String a, String b) {
        assert a != null;
        assert b != null;
        return handleString(a).equals(handleString(b));
    }

    /**
     *  使用字符串倒序
     * @param a
     * @param b
     * @return
     */
    public static boolean compareWithReverse(String a, String b) {
        assert a != null;
        assert b != null;

        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;

        int aSkip = 0;
        int bSkip = 0;
        boolean result = true;

        while (result && aIndex > 0 && bIndex > 0) {
            //如果a字符串遇到backspace,那么继续下一个，同时aSkip 加 1
            if (a.charAt(aIndex) == BACKSPACE_CHARACTER) {
                aIndex -= 1;
                aSkip += 1;
                continue;
            }
            //与a字符串同理
            if (b.charAt(bIndex) == BACKSPACE_CHARACTER) {
                bIndex -= 1;
                bSkip += 1;
                continue;
            }
            //忽略是#的字符
            aIndex -= aSkip;
            bIndex -= bSkip;
            //判断统一位置的字符是否相等
            if (aIndex >= 0 && bIndex >= 0 && a.charAt(aIndex) != b.charAt(bIndex)) {
                result = false;
            }
            aIndex -= 1;
            bIndex -= 1;
        }
        return result;
    }

    private static List<Character> handleString(String a) {
        final List<Character> characters = new ArrayList<>(a.length());
        for (char c : a.toCharArray()) {
            if (c == BACKSPACE_CHARACTER) {
                if (!characters.isEmpty()) {
                    removeLast(characters);
                }
            }
            else {
                characters.add(c);
            }
        }
        return characters;
    }

    private static <T> void removeLast(List<T> list) {
        list.remove(list.size() - 1);
    }
}
