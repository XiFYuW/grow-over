package com.grow.self.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grow.common.page.PageUtils;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressAddDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressDeleteDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressListDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressUpdateDTO;
import com.grow.self.entity.SelfDeliveryAddress;
import com.grow.self.mapper.SelfDeliveryAddressMapper;
import com.grow.self.service.ISelfDeliveryAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

/**
 * 自营收货地址表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@Service
@Slf4j
public class SelfDeliveryAddressServiceImpl extends ServiceImpl<SelfDeliveryAddressMapper, SelfDeliveryAddress> implements ISelfDeliveryAddressService {

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SelfDeliveryAddressListDTO selfDeliveryAddressListDTO) {
        String userId = selfDeliveryAddressListDTO.getUserId();
        Map<String, Object> data = PageUtils.getDateMap(() -> page(PageUtils.getPage(
                new Page<>(), selfDeliveryAddressListDTO.getPage(), selfDeliveryAddressListDTO.getLimit()),
                new QueryWrapper<SelfDeliveryAddress>()
                        .eq("is_del", 0)
                        .eq(!StringUtils.isEmpty(userId), "user_id", userId)
                        .orderByDesc("create_time")));
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(SelfDeliveryAddressAddDTO selfDeliveryAddressAddDTO) {
        String recipientsAddress = getRecipientsAddress(selfDeliveryAddressAddDTO.getRecipientsState(),
                selfDeliveryAddressAddDTO.getRecipientsProvince(), selfDeliveryAddressAddDTO.getRecipientsCity(),
                selfDeliveryAddressAddDTO.getRecipientsDistrict(), selfDeliveryAddressAddDTO.getRecipientsDetail());

        final SelfDeliveryAddress selfDeliveryAddress = new SelfDeliveryAddress(
                DateUtil.toLocalDateTime(DateUtil.date()),
                selfDeliveryAddressAddDTO.getRecipients(),
                selfDeliveryAddressAddDTO.getRecipientsState(),
                selfDeliveryAddressAddDTO.getRecipientsProvince(),
                selfDeliveryAddressAddDTO.getRecipientsCity(),
                selfDeliveryAddressAddDTO.getRecipientsDistrict(),
                Optional.ofNullable(selfDeliveryAddressAddDTO.getRecipientsDetail()).orElse(""),
                0,
                IdUtil.simpleUUID(),
                selfDeliveryAddressAddDTO.getUserId(),
                0,
                recipientsAddress,
                selfDeliveryAddressAddDTO.getRecipientsMobile()
        );
        save(selfDeliveryAddress);
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SelfDeliveryAddressUpdateDTO selfDeliveryAddressUpdateDTO) {

        String recipientsAddress = getRecipientsAddress(selfDeliveryAddressUpdateDTO.getRecipientsState(),
                selfDeliveryAddressUpdateDTO.getRecipientsProvince(), selfDeliveryAddressUpdateDTO.getRecipientsCity(),
                selfDeliveryAddressUpdateDTO.getRecipientsDistrict(), selfDeliveryAddressUpdateDTO.getRecipientsDetail());

        final SelfDeliveryAddress selfDeliveryAddress = new SelfDeliveryAddress(
                selfDeliveryAddressUpdateDTO.getId(),
                selfDeliveryAddressUpdateDTO.getRecipients(),
                selfDeliveryAddressUpdateDTO.getRecipientsState(),
                selfDeliveryAddressUpdateDTO.getRecipientsProvince(),
                selfDeliveryAddressUpdateDTO.getRecipientsCity(),
                selfDeliveryAddressUpdateDTO.getRecipientsDistrict(),
                Optional.ofNullable(selfDeliveryAddressUpdateDTO.getRecipientsDetail()).orElse(""),
                selfDeliveryAddressUpdateDTO.getIsDefault(),
                DateUtil.toLocalDateTime(DateUtil.date()),
                recipientsAddress,
                selfDeliveryAddressUpdateDTO.getRecipientsMobile()
        );

        updateById(selfDeliveryAddress);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(SelfDeliveryAddressDeleteDTO selfDeliveryAddressDeleteDTO) {
        final SelfDeliveryAddress selfDeliveryAddress = new SelfDeliveryAddress();
        selfDeliveryAddress.setId(selfDeliveryAddressDeleteDTO.getId());
        selfDeliveryAddress.setIsDel(1);

        updateById(selfDeliveryAddress);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }

    private String getRecipientsAddress(String recipientsState, String recipientsProvince,
                                        String recipientsCity, String recipientsDistrict,
                                        String recipientsDetail){
        final String recipientsAddress = recipientsState +
                recipientsProvince +
                recipientsCity +
                recipientsDistrict +
                Optional.ofNullable(recipientsDetail).orElse("");
        log.info("详细地址消息：【{}】", recipientsAddress);
        return recipientsAddress;
    }
}
