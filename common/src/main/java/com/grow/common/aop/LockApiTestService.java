package com.grow.common.aop;

import com.grow.common.result.ResponseResultUtils;
import org.springframework.stereotype.Service;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/21 11:40
 */
@Service
public class LockApiTestService {

    @LockApiAn(isSingle = true)
    public Object get(String str){
        return ResponseResultUtils.getResponseResultS("ok");
    }
}
