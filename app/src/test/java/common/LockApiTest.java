package common;

import com.alibaba.fastjson.JSON;
import com.grow.BaseApplication;
import com.grow.common.aop.LockApiTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/21 11:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class LockApiTest {

    @Autowired
    private LockApiTestService lockApiTestService;

    @Test
    public void lockApi() throws Exception{

        new Thread(()-> log.info(JSON.toJSONString(lockApiTestService.get("Thread-1"))) ,"Thread-1").start();

        new Thread(()-> log.info(JSON.toJSONString(lockApiTestService.get("Thread-2"))) ,"Thread-2").start();

        new Thread(()-> log.info(JSON.toJSONString(lockApiTestService.get("Thread-3"))) ,"Thread-3").start();

        new Thread(()-> log.info(JSON.toJSONString(lockApiTestService.get("Thread-4"))) ,"Thread-4").start();

        Thread.sleep(100000L);
    }
}
