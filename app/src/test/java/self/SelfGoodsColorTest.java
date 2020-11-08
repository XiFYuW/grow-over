package self;

import com.alibaba.fastjson.JSON;
import com.grow.BaseApplication;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorAddDTO;
import com.grow.self.service.ISelfGoodsColorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/23 13:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class SelfGoodsColorTest {

    @Autowired
    private ISelfGoodsColorService iSelfGoodsColorService;

    @Test
    public void add(){
        SelfGoodsColorAddDTO selfGoodsColorAddDTO = new SelfGoodsColorAddDTO();
        selfGoodsColorAddDTO.setColorName("颜色二");
        selfGoodsColorAddDTO.setColorPrice(new BigDecimal(10));
        selfGoodsColorAddDTO.setGoodsNo("GO-SP201023261000");
        log.info(JSON.toJSONString(iSelfGoodsColorService.add(selfGoodsColorAddDTO)));
    }
}
