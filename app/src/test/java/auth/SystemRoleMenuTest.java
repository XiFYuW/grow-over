package auth;

import cn.hutool.core.date.DateUtil;
import com.grow.BaseApplication;
import com.grow.auth.system.entity.SystemRoleMenu;
import com.grow.auth.system.mapper.SystemRoleMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/06 17:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class SystemRoleMenuTest {
    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;

    @Test
    public void addSystemRoleMenu(){

        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setCreateTime(DateUtil.toLocalDateTime(DateUtil.date()));
        systemRoleMenu.setSystemMenuId(1);
        systemRoleMenu.setSystemRoleId(1);
        systemRoleMenuMapper.insert(systemRoleMenu);
    }
}
