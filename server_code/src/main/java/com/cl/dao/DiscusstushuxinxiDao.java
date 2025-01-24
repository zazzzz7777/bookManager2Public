package com.cl.dao;

import com.cl.entity.DiscusstushuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusstushuxinxiView;


/**
 * 图书信息评论表
 * 
 * @author 
 * @email 
 * @date 2024-11-27 15:48:59
 */
public interface DiscusstushuxinxiDao extends BaseMapper<DiscusstushuxinxiEntity> {
	
	List<DiscusstushuxinxiView> selectListView(@Param("ew") Wrapper<DiscusstushuxinxiEntity> wrapper);

	List<DiscusstushuxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusstushuxinxiEntity> wrapper);
	
	DiscusstushuxinxiView selectView(@Param("ew") Wrapper<DiscusstushuxinxiEntity> wrapper);


}
