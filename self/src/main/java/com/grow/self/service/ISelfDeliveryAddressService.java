package com.grow.self.service;

import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressAddDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressDeleteDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressListDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressUpdateDTO;
import com.grow.self.entity.SelfDeliveryAddress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 自营收货地址表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
public interface ISelfDeliveryAddressService extends IService<SelfDeliveryAddress> {

    ResponseResult list(SelfDeliveryAddressListDTO selfDeliveryAddressListDTO);

    ResponseResult add(SelfDeliveryAddressAddDTO selfDeliveryAddressAddDTO);

    ResponseResult update(SelfDeliveryAddressUpdateDTO selfDeliveryAddressUpdateDTO);

    ResponseResult delete(SelfDeliveryAddressDeleteDTO selfDeliveryAddressDeleteDTO);

}
