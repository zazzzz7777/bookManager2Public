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
import com.cl.entity.OrdersEntity;
import com.cl.service.OrdersService;

import com.cl.entity.TushuxinxiEntity;
import com.cl.entity.view.TushuxinxiView;

import com.cl.service.TushuxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 图书信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-11-27 15:48:58
 */
@RestController
@RequestMapping("/tushuxinxi")
public class TushuxinxiController {
    @Autowired
    private TushuxinxiService tushuxinxiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;





    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TushuxinxiEntity tushuxinxi,
                                                                                                                                                                                                        HttpServletRequest request){
                                    EntityWrapper<TushuxinxiEntity> ew = new EntityWrapper<TushuxinxiEntity>();
                                                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = tushuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tushuxinxi), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TushuxinxiEntity tushuxinxi, 
		HttpServletRequest request){
        EntityWrapper<TushuxinxiEntity> ew = new EntityWrapper<TushuxinxiEntity>();

		PageUtils page = tushuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tushuxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TushuxinxiEntity tushuxinxi){
       	EntityWrapper<TushuxinxiEntity> ew = new EntityWrapper<TushuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tushuxinxi, "tushuxinxi")); 
        return R.ok().put("data", tushuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TushuxinxiEntity tushuxinxi){
        EntityWrapper< TushuxinxiEntity> ew = new EntityWrapper< TushuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tushuxinxi, "tushuxinxi")); 
		TushuxinxiView tushuxinxiView =  tushuxinxiService.selectView(ew);
		return R.ok("查询图书信息成功").put("data", tushuxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TushuxinxiEntity tushuxinxi = tushuxinxiService.selectById(id);
		tushuxinxi.setClicktime(new Date());
		tushuxinxiService.updateById(tushuxinxi);
		tushuxinxi = tushuxinxiService.selectView(new EntityWrapper<TushuxinxiEntity>().eq("id", id));
        return R.ok().put("data", tushuxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TushuxinxiEntity tushuxinxi = tushuxinxiService.selectById(id);
		tushuxinxi.setClicktime(new Date());
		tushuxinxiService.updateById(tushuxinxi);
		tushuxinxi = tushuxinxiService.selectView(new EntityWrapper<TushuxinxiEntity>().eq("id", id));
        return R.ok().put("data", tushuxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TushuxinxiEntity tushuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tushuxinxi);
        tushuxinxiService.insert(tushuxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TushuxinxiEntity tushuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tushuxinxi);
        tushuxinxiService.insert(tushuxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TushuxinxiEntity tushuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tushuxinxi);
        tushuxinxiService.updateById(tushuxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tushuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<TushuxinxiEntity> wrapper = new EntityWrapper<TushuxinxiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = tushuxinxiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,TushuxinxiEntity tushuxinxi, HttpServletRequest request,String pre){
        EntityWrapper<TushuxinxiEntity> ew = new EntityWrapper<TushuxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = tushuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tushuxinxi), params), params));
        return R.ok().put("data", page);
    }

        /**
     * 按用户购买推荐
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,TushuxinxiEntity tushuxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String goodtypeColumn = "storeup_number";
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "tushuxinxi").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<TushuxinxiEntity> tushuxinxiList = new ArrayList<TushuxinxiEntity>();
	//去重
    	List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
    	for(OrdersEntity o1 : orders) {
    		boolean addFlag = true;
    		for(OrdersEntity o2 : ordersDist) {
    			if(o1.getGoodid()==o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
    				addFlag = false;
    				break;
    			}
    		}
    		if(addFlag) ordersDist.add(o1);
    	}
        if(ordersDist!=null && ordersDist.size()>0) {
                for(OrdersEntity o : ordersDist) {
                        tushuxinxiList.addAll(tushuxinxiService.selectList(new EntityWrapper<TushuxinxiEntity>().eq(goodtypeColumn, o.getGoodtype())));
                }
        }
        EntityWrapper<TushuxinxiEntity> ew = new EntityWrapper<TushuxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = tushuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tushuxinxi), params), params));
        List<TushuxinxiEntity> pageList = (List<TushuxinxiEntity>)page.getList();
        if(tushuxinxiList.size()<limit) {
                int toAddNum = (limit-tushuxinxiList.size())<=pageList.size()?(limit-tushuxinxiList.size()):pageList.size();
                for(TushuxinxiEntity o1 : pageList) {
                    boolean addFlag = true;
                    for(TushuxinxiEntity o2 : tushuxinxiList) {
                        if(o1.getId().intValue()==o2.getId().intValue()) {
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag) {
                        tushuxinxiList.add(o1);
                        if(--toAddNum==0) break;
                    }   
                }
        } else if(tushuxinxiList.size()>limit) {
            tushuxinxiList = tushuxinxiList.subList(0, limit);
        }
        page.setList(tushuxinxiList);
        return R.ok().put("data", page);
    }








}
