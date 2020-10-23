package com.grow.pay.wx;

import com.grow.pay.wx.tool.WXPayUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 9:43
 */
public class WxNotifyMap {

    public static Map<String, String> getParams(final HttpServletRequest request) throws Exception {
        final InputStream inStream = request.getInputStream();
        final ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String notifyData = new String(outSteam.toByteArray(), StandardCharsets.UTF_8);
        outSteam.close();
        inStream.close();
        return WXPayUtil.xmlToMap(notifyData);
    }
}
