package com.grow.pay.connce.store;

import java.util.List;
import java.util.Map;

public interface ConsequenceMapper {

    int insertSelective(ConsequencePay record);

    ConsequencePay selectByPrimaryKey(Long id);

    ConsequencePay selectByOrderNo(String orderNo);

    int updateStatus(Map<String, Object> map);

    List<ConsequencePay> selectByIsPerform();
}
