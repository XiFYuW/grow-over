package pay;

import com.grow.BaseApplication;
import com.grow.pay.ali.service.AliPayEasyService;
import com.grow.pay.connce.Consequence;
import com.grow.pay.connce.OrderQueryPoolExecutorService;
import com.grow.pay.connce.OrderQueryPoolService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/22 14:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class ConsequenceTest {

    @Autowired
    private AliPayEasyService aliPayEasyService;

    @Test
    public void consequenceTest() throws Exception{
//        OrderQueryPoolExecutorService orderQueryPoolExecutorService = new OrderQueryPoolService();
//
//        orderQueryPoolExecutorService.start();
//
//        for (int i = 0; i < 10; i++) {
//            final Consequence consequence = new Consequence(true, "ZM20092713552115010003", 1, aliPayEasyService);
//            orderQueryPoolExecutorService.submitRequest(consequence);
//        }
//
//
//        Thread.sleep(60000);
//
//        orderQueryPoolExecutorService.shutdown();

    }
}
