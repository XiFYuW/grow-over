package auth;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.grow.BaseApplication;
import com.grow.auth.system.entity.SystemMenu;
import com.grow.auth.system.mapper.SystemMenuMapper;
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
public class SystemMenuTest {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Test
    public void addSystemMenu(){
        SystemMenu systemMenu = SystemMenu.builder()
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .systemPermissions("user:getInfo")
                .systemPic("")
                .systemPath("/")
                .systemMenuName("获取登录用户信息")
                .systemMenuId(IdUtil.simpleUUID())
                .systemMenuPid(0L)
                .build();
        systemMenuMapper.insert(systemMenu);
    }
}
