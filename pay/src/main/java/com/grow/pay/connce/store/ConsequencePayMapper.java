package com.grow.pay.connce.store;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ConsequencePayMapper {

    int insertSelective(ConsequencePay record);

    ConsequencePay selectByPrimaryKey(Long id);

    ConsequencePay selectByOrderNo(String orderNo);

    int updateStatus(Map<String, Object> map);

    List<ConsequencePay> selectByIsPerform();
}
