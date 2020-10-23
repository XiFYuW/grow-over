package com.grow.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/3/3 9:41
 */
public class ThrowableUtil {
    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
