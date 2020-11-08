package auth;

import com.grow.BaseApplication;
import com.grow.auth.user.entity.UserLoginSecurity;
import com.grow.auth.user.mapper.UserLoginSecurityMapper;
import com.grow.common.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/27 14:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class UserTest {

    @Autowired
    private UserLoginSecurityMapper userLoginSecurityMapper;

    @Test
    public void addUser(){
        UserLoginSecurity userLoginSecurity = new UserLoginSecurity();
        userLoginSecurity.setEmail("xif_yuw@163.com");
        userLoginSecurity.setUserName("user02");
        userLoginSecurity.setPassword(EncryptUtils.encryptPassword("123456"));
        userLoginSecurityMapper.insert(userLoginSecurity);
    }

    @Test
    public void net() throws UnsupportedEncodingException {
        String s = "%F0%9F%87%BE.";
        log.info(URLDecoder.decode(s, "utf-8"));
    }

}
