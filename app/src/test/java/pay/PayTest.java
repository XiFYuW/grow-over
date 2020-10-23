package pay;

import cn.hutool.core.util.IdUtil;
import com.grow.BaseApplication;
import com.grow.pay.ali.AliPayRequest;
import com.grow.pay.ali.service.AliPayEasyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/27 16:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class PayTest {

    @Autowired
    private AliPayEasyService aliPayEasyService;

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    @Test
    public void refund(){
        final AliPayRequest aliPayRequest = AliPayRequest.builder()
                .outTradeNo("ZM20092713552115010003")
                .refundAmount(DF.format(new BigDecimal(0.1)))
                .RequestNo(IdUtil.fastSimpleUUID())
                .build();
        aliPayEasyService.refund(aliPayRequest, (map) -> {

        });
    }

}
