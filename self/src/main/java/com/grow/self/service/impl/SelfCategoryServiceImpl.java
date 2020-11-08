package com.grow.self.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.service.UserExecuteService;
import com.grow.common.page.PageUtils;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.self.dto.selfCategory.*;
import com.grow.self.entity.SelfCategory;
import com.grow.self.mapper.SelfCategoryMapper;
import com.grow.self.service.ISelfCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.self.vo.selfCategory.SelfCategoryGetSelectDataVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 自营商品分类
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@Service
public class SelfCategoryServiceImpl extends ServiceImpl<SelfCategoryMapper, SelfCategory> implements ISelfCategoryService {

    private final SelfCategoryMapper selfCategoryMapper;

    private final UserExecuteService userExecuteService;

    public SelfCategoryServiceImpl(SelfCategoryMapper selfCategoryMapper, UserExecuteService userExecuteService) {
        this.selfCategoryMapper = selfCategoryMapper;
        this.userExecuteService = userExecuteService;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SelfCategoryListDTO selfCategoryListDTO) {
        // final UserInfoSecurity userInfoSecurity = userExecuteService.getUserInfo();
        String categoryName = selfCategoryListDTO.getCategoryName();
        Map<String, Object> params = new HashMap<>();
        params.put("shop_id", selfCategoryListDTO.getShopId());
        params.put("category_level", selfCategoryListDTO.getCategoryLevel());
        params.put("category_parent_id", selfCategoryListDTO.getCategoryParentId());
        Map<String, Object> data = PageUtils.getDateMap(() -> page(PageUtils.getPage(
                new Page<>(), selfCategoryListDTO.getPage(), selfCategoryListDTO.getLimit()),
                new QueryWrapper<SelfCategory>()
                        .eq("is_del", 0)
                        .allEq(params, false)
                        .likeRight(!StringUtils.isEmpty(categoryName), "category_name", categoryName)
                        .orderByDesc("create_time")));
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(SelfCategoryAddDTO selfCategoryAddDTO) {
        final SelfCategory selfCategory = new SelfCategory();
        selfCategory.setCreateTime(DateUtil.toLocalDateTime(DateUtil.date()));
        selfCategory.setIsDel(0);
        selfCategory.setParentId(selfCategoryAddDTO.getParentId());
        selfCategory.setShopId(selfCategoryAddDTO.getShopId());
        Optional.ofNullable(selfCategoryAddDTO.getCategoryLevel()).ifPresent(selfCategory::setCategoryLevel);
        Optional.ofNullable(selfCategoryAddDTO.getCategoryParentId()).ifPresent(selfCategory::setCategoryParentId);
        selfCategory.setCategorySelfId(IdUtil.simpleUUID());
        selfCategory.setCategoryPic(selfCategoryAddDTO.getCategoryPic());
        selfCategory.setCategoryName(selfCategoryAddDTO.getCategoryName());
        save(selfCategory);
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SelfCategoryUpdateDTO selfCategoryUpdateDTO) {
        final SelfCategory selfCategory = new SelfCategory();
        selfCategory.setId(selfCategoryUpdateDTO.getId());
        selfCategory.setUpdateTime(DateUtil.toLocalDateTime(DateUtil.date()));
        Optional.ofNullable(selfCategoryUpdateDTO.getCategoryLevel()).ifPresent(selfCategory::setCategoryLevel);
        Optional.ofNullable(selfCategoryUpdateDTO.getCategoryParentId()).ifPresent(selfCategory::setCategoryParentId);
        selfCategory.setCategoryPic(selfCategoryUpdateDTO.getCategoryPic());
        selfCategory.setCategoryName(selfCategoryUpdateDTO.getCategoryName());
        updateById(selfCategory);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(SelfCategoryDeleteDTO selfCategoryDeleteDTO) {
        final SelfCategory selfCategory = new SelfCategory();
        selfCategory.setId(selfCategoryDeleteDTO.getId());
        selfCategory.setIsDel(1);
        updateById(selfCategory);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult getSelectData(SelfCategoryGetSelectDataDTO selfCategoryGetSelectDataDTO) {
        Map<String, Object> params = new HashMap<>();
        params.put("parent_id", selfCategoryGetSelectDataDTO.getParentId());
        params.put("shop_id", selfCategoryGetSelectDataDTO.getShopId());
        params.put("category_level", selfCategoryGetSelectDataDTO.getCategoryLevel());
        params.put("category_parent_id", selfCategoryGetSelectDataDTO.getCategoryParentId());

        List<SelfCategory> data = PageUtils.getDateMapByRecords(() -> page(PageUtils.getPage(
                new Page<>(), selfCategoryGetSelectDataDTO.getPage(), selfCategoryGetSelectDataDTO.getLimit()),
                new QueryWrapper<SelfCategory>().allEq(params, false))
        );

        // 是否有子级分类 1：有 0：无
        List<SelfCategoryGetSelectDataVO> selectData = data.stream().map(selfCategory -> {
            Integer count = selfCategoryMapper.selectCount(new QueryWrapper<SelfCategory>()
                    .eq("category_parent_id", selfCategory.getCategorySelfId()));
            return new SelfCategoryGetSelectDataVO(selfCategory.getCategoryName(),
                    selfCategory.getCategorySelfId(), count.compareTo(0) > 0 ? 1 : 0);
        }).collect(Collectors.toList());

        return ResponseResultUtils.getResponseResultS("查询成功", selectData);
    }

}
