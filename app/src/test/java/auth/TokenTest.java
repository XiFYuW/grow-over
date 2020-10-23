package auth;

import com.grow.BaseApplication;
import com.grow.auth.security.jwt.JwtContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 10:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class TokenTest {

    @Autowired
    private JwtContext jwtContext;

    @Test
    public void tokenTest(){
        String token = jwtContext.getTokenHMAC256();
        log.info("token：【{}】", token);
        jwtContext.verifyTokenHMAC256(token);
    }

}
