package com.grow.common.aop;

import com.grow.common.result.ResponseResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/21 11:40
 */
@Service
@Slf4j
public class LockApiTestService {

    @LockApiAn(isSingle = true)
    public Object get(String str){
        return ResponseResultUtils.getResponseResultS("ok");
    }
}
