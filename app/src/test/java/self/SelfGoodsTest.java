package self;

import com.alibaba.fastjson.JSON;
import com.grow.BaseApplication;
import com.grow.self.dto.selfGoods.SelfGoodsAddDTO;
import com.grow.self.service.ISelfGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/23 13:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class SelfGoodsTest {

    @Autowired
    private ISelfGoodsService iSelfGoodsService;

    @Test
    public void add(){

        SelfGoodsAddDTO selfGoodsAddDTO = new SelfGoodsAddDTO();
        selfGoodsAddDTO.setGoodsDetails("");
        selfGoodsAddDTO.setGoodsIntroduce("");
        selfGoodsAddDTO.setGoodsName("测试一");
        selfGoodsAddDTO.setGoodsNo("");
        selfGoodsAddDTO.setGoodsTypeId("");
        selfGoodsAddDTO.setGoodsSupply("");
        selfGoodsAddDTO.setShopId("");
        log.info(JSON.toJSONString(iSelfGoodsService.add(selfGoodsAddDTO)));
    }

}
