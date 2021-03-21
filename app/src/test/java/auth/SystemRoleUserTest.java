package auth;

import cn.hutool.core.date.DateUtil;
import com.grow.BaseApplication;
import com.grow.auth.system.entity.SystemRoleUser;
import com.grow.auth.system.mapper.SystemRoleUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/06 16:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class SystemRoleUserTest {

    @Autowired
    private SystemRoleUserMapper systemRoleUserMapper;

    @Test
    public void addSystemRoleUser(){
        SystemRoleUser systemRoleUser = new SystemRoleUser();
        systemRoleUser.setCreateTime(DateUtil.toLocalDateTime(DateUtil.date()));
        systemRoleUser.setSystemRoleId(1);
        systemRoleUser.setUserId(1);
        systemRoleUserMapper.insert(systemRoleUser);
    }
}
