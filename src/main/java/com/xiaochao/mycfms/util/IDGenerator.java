package com.xiaochao.mycfms.util;

import java.util.UUID;

public class IDGenerator {
    private IDGenerator() {
    }

    /**
     * 
     * @Title: getUID
     * @Description: (获取UUID)
     * @return
     * @author qh
     */
    public static String getUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 
     * @Title: getUID
     * @Description: (获取UUID数组)
     * @param number 数组大小
     * @return
     * @author qh
     */
    public static String[] getUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUID();
        }
        return ss;
    }

}
