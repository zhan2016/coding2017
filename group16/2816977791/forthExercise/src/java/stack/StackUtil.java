package stack;

import java.util.Stack;

public class StackUtil {


    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
        Stack tmp = clone(s);
        s.clear();
        while (!tmp.isEmpty()) {
            s.push(tmp.pop());
        }
    }

    private static Stack clone(Stack s) {
        return (Stack) s.clone();
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
        reverse(s);
        Stack result = clone(s);
        s.clear();
        while (!result.isEmpty()) {
            if (result.peek() != o) {
                s.push(result.pop());
            } else {
                result.pop();
            }
        }
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {
        Stack result = clone(s);
        if (len > result.size()) {
            len = result.size();
        }
        Object[] objects = new Object[len];
        for (int i = 0; i < len; i++) {
            objects[i] = s.pop();
        }
        return objects;
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     *
     * @param s
     * @return
     */
    public static boolean isValidPairs(String s) {
        Stack stack = new Stack();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                switch (ch) {
                    case ')':
                        if ('(' == (char) stack.pop()) {
                            continue;
                        }
                        return false;
                    case ']':
                        if ('[' == (char) stack.pop()) {
                            continue;
                        }
                        return false;
                    case '}':
                        if ('{' == (char) stack.pop()) {
                            continue;
                        }
                        return false;
                    default:
                        break;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }


}