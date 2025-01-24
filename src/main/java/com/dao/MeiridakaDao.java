package com.dao;

import com.entity.MeiridakaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MeiridakaView;

/**
 * 每日打卡 Dao 接口
 *
 * @author 
 */
public interface MeiridakaDao extends BaseMapper<MeiridakaEntity> {

   List<MeiridakaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
