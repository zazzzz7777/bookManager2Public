package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.TushufenleiEntity;
import com.cl.entity.view.TushufenleiView;

import com.cl.service.TushufenleiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 图书分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-11-27 15:48:58
 */
@RestController
@RequestMapping("/tushufenlei")
public class TushufenleiController {
    @Autowired
    private TushufenleiService tushufenleiService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TushufenleiEntity tushufenlei,
                                            HttpServletRequest request){
                                    EntityWrapper<TushufenleiEntity> ew = new EntityWrapper<TushufenleiEntity>();
                                                        
        
        
        PageUtils page = tushufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tushufenlei), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TushufenleiEntity tushufenlei, 
		HttpServletRequest request){
        EntityWrapper<TushufenleiEntity> ew = new EntityWrapper<TushufenleiEntity>();

		PageUtils page = tushufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tushufenlei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TushufenleiEntity tushufenlei){
       	EntityWrapper<TushufenleiEntity> ew = new EntityWrapper<TushufenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tushufenlei, "tushufenlei")); 
        return R.ok().put("data", tushufenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TushufenleiEntity tushufenlei){
        EntityWrapper< TushufenleiEntity> ew = new EntityWrapper< TushufenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tushufenlei, "tushufenlei")); 
		TushufenleiView tushufenleiView =  tushufenleiService.selectView(ew);
		return R.ok("查询图书分类成功").put("data", tushufenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TushufenleiEntity tushufenlei = tushufenleiService.selectById(id);
		tushufenlei = tushufenleiService.selectView(new EntityWrapper<TushufenleiEntity>().eq("id", id));
        return R.ok().put("data", tushufenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TushufenleiEntity tushufenlei = tushufenleiService.selectById(id);
		tushufenlei = tushufenleiService.selectView(new EntityWrapper<TushufenleiEntity>().eq("id", id));
        return R.ok().put("data", tushufenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TushufenleiEntity tushufenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tushufenlei);
        tushufenleiService.insert(tushufenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TushufenleiEntity tushufenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tushufenlei);
        tushufenleiService.insert(tushufenlei);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TushufenleiEntity tushufenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tushufenlei);
        tushufenleiService.updateById(tushufenlei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tushufenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
