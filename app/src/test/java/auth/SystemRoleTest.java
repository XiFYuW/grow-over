package auth;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.grow.BaseApplication;
import com.grow.auth.system.entity.SystemRole;
import com.grow.auth.system.mapper.SystemRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/06 16:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class SystemRoleTest {
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Test
    public void addSystemRole(){
        SystemRole systemRole = new SystemRole();
        systemRole.setCreateTime(DateUtil.toLocalDateTime(DateUtil.date()));
        systemRole.setSystemRoleId(IdUtil.simpleUUID());
        systemRole.setSystemRoleLevel(0);
        systemRole.setSystemRoleName("系统管理员");
        systemRoleMapper.insert(systemRole);
    }
}
