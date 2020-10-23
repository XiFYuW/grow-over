package com.grow.self.controller;

import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressAddDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressDeleteDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressListDTO;
import com.grow.self.dto.selfDeliveryAddress.SelfDeliveryAddressUpdateDTO;
import com.grow.self.service.ISelfDeliveryAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 自营收货地址表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/selfDeliveryAddress")
@Api(tags = "自营收货地址")
public class SelfDeliveryAddressController {

    private final ISelfDeliveryAddressService iSelfDeliveryAddressService;

    public SelfDeliveryAddressController(ISelfDeliveryAddressService iSelfDeliveryAddressService) {
        this.iSelfDeliveryAddressService = iSelfDeliveryAddressService;
    }

    @ApiOperation(value="自营收货地址分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfDeliveryAddress/list")
    public ResponseResult selfDeliveryAddressList(@Valid SelfDeliveryAddressListDTO selfDeliveryAddressListDTO){
        return iSelfDeliveryAddressService.list(selfDeliveryAddressListDTO);
    }

    @ApiOperation(value="自营收货地址新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfDeliveryAddress/add")
    public ResponseResult selfDeliveryAddressAdd(@Valid SelfDeliveryAddressAddDTO selfDeliveryAddressAddDTO){
        return iSelfDeliveryAddressService.add(selfDeliveryAddressAddDTO);
    }

    @ApiOperation(value="自营收货地址修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfDeliveryAddress/update")
    public ResponseResult selfDeliveryAddressUpdate(@Valid SelfDeliveryAddressUpdateDTO selfDeliveryAddressUpdateDTO){
        return iSelfDeliveryAddressService.update(selfDeliveryAddressUpdateDTO);
    }

    @ApiOperation(value="自营收货地址删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfDeliveryAddress/delete")
    public ResponseResult selfDeliveryAddressDelete(@Valid SelfDeliveryAddressDeleteDTO selfDeliveryAddressDeleteDTO){
        return iSelfDeliveryAddressService.delete(selfDeliveryAddressDeleteDTO);
    }

}

