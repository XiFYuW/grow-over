package com.grow.pay.ali.service;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.grow.pay.ali.AliNotifyMap;
import com.grow.pay.exception.AliRuntimeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 10:45
 */
@Service
public class AliBuildService {

    private final Config config;

    public AliBuildService(@Qualifier("ailConfig") Config config) {
        this.config = config;
    }

    public Map<String, Object> buildPay(final AliBuildPayContext ailBuildPayContext){
        Factory.setOptions(config);
        try {
            final AlipayTradeAppPayResponse alipayTradeAppPayResponse = ailBuildPayContext.buildPayContext();
            if (ResponseChecker.success(alipayTradeAppPayResponse)) {
                return alipayTradeAppPayResponse.toMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AliRuntimeException(e.getMessage());
        }
        return null;
    }

    public void buildNotifyContext(HttpServletRequest request, final AliBuildNotifyContext ailBuildNotifyContext){
        final Map<String, String> params = AliNotifyMap.getParams(request);
        Factory.setOptions(config);

        try {
            ailBuildNotifyContext.buildNotifyContext(params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AliRuntimeException(e.getMessage());
        }
    }

    public void buildGeneral(final AliBuildGeneralContext ailBuildGeneralContext) {
        Factory.setOptions(config);
        try {
            ailBuildGeneralContext.buildGeneralContext();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AliRuntimeException(e.getMessage());
        }
    }

    public void buildGenericExecute(final AliBuildGenericExecuteContext ailBuildGenericExecuteContext){
        Factory.setOptions(config);
        try {
            ailBuildGenericExecuteContext.buildGenericExecuteContext();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AliRuntimeException(e.getMessage());
        }
    }

    public interface AliBuildGeneralContext{
        void buildGeneralContext() throws Exception;
    }

    public interface AliBuildPayContext{
        AlipayTradeAppPayResponse buildPayContext() throws Exception;
    }

    public interface AliBuildNotifyContext{
        void buildNotifyContext(Map<String, String> params) throws Exception;
    }

    public interface AliBuildGenericExecuteContext{
        void buildGenericExecuteContext() throws Exception;
    }
}
