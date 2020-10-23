package self;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.grow.BaseApplication;
import com.grow.self.entity.SelfExpress;
import com.grow.self.mapper.SelfExpressMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/13 16:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
@ContextConfiguration
@Slf4j
public class SelfExpressTest {

    @Autowired
    private SelfExpressMapper selfExpressMapper;

    @Test
    public void testLoad(){
        ExcelReader excelReader = ExcelUtil.getReader("D:\\WeChat Files\\WeChat Files\\wxid_gj9xiw04x9xh22\\FileStorage\\File\\2020-10\\快递100快递公司标准编码-20201013162953(1).xlsx",
                0);

        int rowCount = excelReader.getRowCount();
        for(int r = 2; r < rowCount; r++) {
            SelfExpress selfExpress = new SelfExpress();
            selfExpress.setCompanyType(getCompanyType(excelReader.getCell(2, r).getStringCellValue()));
            selfExpress.setCreateTime(DateUtil.toLocalDateTime(DateUtil.date()));
            selfExpress.setExpressCompany(excelReader.getCell(0, r).getStringCellValue());
            selfExpress.setExpressNumber(excelReader.getCell(1, r).getStringCellValue());
            selfExpressMapper.insert(selfExpress);
        }

    }

    private int getCompanyType(String companyType){
        if ("国内运输商".equals(companyType)){
            return 1;
        }

        if ("国际运输商".equals(companyType)){
            return 2;
        }

        if ("国际邮政".equals(companyType)){
            return 3;
        }

        return 0;

    }

    @Test
    public void testLoadJson(){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("callbackurl", "http://www.xxxxx.com/callback");
        Map<String, Object> param = new HashMap<>();
        param.put("company", "jd");
        param.put("number", "JDVB05785666006");
        param.put("key", "nVvlvUjZ7092");
        param.put("parameters", parameters);
        Map<String, Object> map = new HashMap<>();
        map.put("schema", "json");
        map.put("param", param);
        System.out.println(JSON.toJSONString(map));
    }
}
