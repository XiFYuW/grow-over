package com.grow.pay.wx.service;

import com.grow.pay.wx.WxNotifyMap;
import com.grow.pay.exception.WxRuntimeException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 10:45
 */
@Service
public class WxBuildService {

    public Map<String, String> buildPay(final WxBuildPayContext wxBuildPayContext){
        try {
            return wxBuildPayContext.buildPayContext();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WxRuntimeException(e.getMessage());
        }
    }

    public Map<String, String> buildGeneral(final WxBuildGeneralContext wxBuildGeneralContext) {
        try {
            final Map<String, String> data = wxBuildGeneralContext.buildGeneralContext();
            if (!data.get("return_code").equals("SUCCESS")) {
                throw new WxRuntimeException(data.get("return_msg"));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WxRuntimeException(e.getMessage());
        }
    }

    public void buildNotifyContext(final HttpServletRequest request, final WxBuildNotifyContext wxBuildNotifyContext){
        try {
            final Map<String, String> params = WxNotifyMap.getParams(request);
            wxBuildNotifyContext.buildNotifyContext(params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WxRuntimeException(e.getMessage());
        }
    }

    public interface WxBuildGeneralContext{
        Map<String, String> buildGeneralContext() throws Exception;
    }

    public interface WxBuildPayContext{
        Map<String, String> buildPayContext() throws Exception;
    }

    public interface WxBuildNotifyContext{
        void buildNotifyContext(Map<String, String> params) throws Exception;
    }

}
