package com.grow.self.service;

import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfCategory.*;
import com.grow.self.entity.SelfCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 自营商品分类
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
public interface ISelfCategoryService extends IService<SelfCategory> {

    ResponseResult list(SelfCategoryListDTO selfCategoryListDTO);

    ResponseResult add(SelfCategoryAddDTO selfCategoryAddDTO);

    ResponseResult update(SelfCategoryUpdateDTO selfCategoryUpdateDTO);

    ResponseResult delete(SelfCategoryDeleteDTO selfCategoryDeleteDTO);

    ResponseResult getSelectData(SelfCategoryGetSelectDataDTO selfCategoryGetSelectDataDTO);

}
