package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.AboutusEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.AboutusView;


/**
 * 发展历程
 *
 * @author 
 * @email 
 * @date 2024-11-27 15:48:59
 */
public interface AboutusService extends IService<AboutusEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<AboutusView> selectListView(Wrapper<AboutusEntity> wrapper);
   	
   	AboutusView selectView(@Param("ew") Wrapper<AboutusEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<AboutusEntity> wrapper);
   	
   
}

