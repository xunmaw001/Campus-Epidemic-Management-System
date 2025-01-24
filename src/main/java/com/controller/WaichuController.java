
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 外出信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/waichu")
public class WaichuController {
    private static final Logger logger = LoggerFactory.getLogger(WaichuController.class);

    private static final String TABLE_NAME = "waichu";

    @Autowired
    private WaichuService waichuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DakaService dakaService;//健康打卡
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//交流论坛
    @Autowired
    private GonggaoService gonggaoService;//通知公告
    @Autowired
    private LaoshiService laoshiService;//老师
    @Autowired
    private MeiridakaService meiridakaService;//每日打卡
    @Autowired
    private QingjiaService qingjiaService;//请假
    @Autowired
    private YonghuService yonghuService;//学生
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = waichuService.queryPage(params);

        //字典表数据转换
        List<WaichuView> list =(List<WaichuView>)page.getList();
        for(WaichuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WaichuEntity waichu = waichuService.selectById(id);
        if(waichu !=null){
            //entity转view
            WaichuView view = new WaichuView();
            BeanUtils.copyProperties( waichu , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(waichu.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(waichu.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaoshiId(laoshi.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody WaichuEntity waichu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,waichu:{}",this.getClass().getName(),waichu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            waichu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("老师".equals(role))
            waichu.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<WaichuEntity> queryWrapper = new EntityWrapper<WaichuEntity>()
            .eq("yonghu_id", waichu.getYonghuId())
            .eq("laoshi_id", waichu.getLaoshiId())
            .eq("waichu_types", waichu.getWaichuTypes())
            .eq("waichu_mudidi", waichu.getWaichuMudidi())
            .eq("waichu_chufadi", waichu.getWaichuChufadi())
            .eq("waichu_shenti_types", waichu.getWaichuShentiTypes())
            .eq("waichu_chufa_time", new SimpleDateFormat("yyyy-MM-dd").format(waichu.getWaichuChufaTime()))
            .eq("waichu_daoda_time", new SimpleDateFormat("yyyy-MM-dd").format(waichu.getWaichuDaodaTime()))
            .in("waichu_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WaichuEntity waichuEntity = waichuService.selectOne(queryWrapper);
//        if(waichuEntity==null){
            waichu.setWaichuYesnoTypes(1);
            waichu.setInsertTime(new Date());
            waichu.setCreateTime(new Date());
            waichuService.insert(waichu);
            return R.ok();
//        }else {
//            if(waichuEntity.getWaichuYesnoTypes()==1)
//                return R.error(511,"有相同的待审核的数据");
//            else if(waichuEntity.getWaichuYesnoTypes()==2)
//                return R.error(511,"有相同的审核通过的数据");
//            else
//                return R.error(511,"表中有相同数据");
//        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WaichuEntity waichu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,waichu:{}",this.getClass().getName(),waichu.toString());
        WaichuEntity oldWaichuEntity = waichuService.selectById(waichu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            waichu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("老师".equals(role))
//            waichu.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(waichu.getWaichuText()) || "null".equals(waichu.getWaichuText())){
                waichu.setWaichuText(null);
        }
        if("".equals(waichu.getWaichuYesnoText()) || "null".equals(waichu.getWaichuYesnoText())){
                waichu.setWaichuYesnoText(null);
        }

            waichuService.updateById(waichu);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody WaichuEntity waichuEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,waichuEntity:{}",this.getClass().getName(),waichuEntity.toString());

        WaichuEntity oldWaichu = waichuService.selectById(waichuEntity.getId());//查询原先数据

//        if(waichuEntity.getWaichuYesnoTypes() == 2){//通过
//            waichuEntity.setWaichuTypes();
//        }else if(waichuEntity.getWaichuYesnoTypes() == 3){//拒绝
//            waichuEntity.setWaichuTypes();
//        }
        waichuEntity.setWaichuShenheTime(new Date());//审核时间
        waichuService.updateById(waichuEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WaichuEntity> oldWaichuList =waichuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        waichuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<WaichuEntity> waichuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            WaichuEntity waichuEntity = new WaichuEntity();
//                            waichuEntity.setYonghuId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            waichuEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            waichuEntity.setWaichuText(data.get(0));                    //申请理由 要改的
//                            waichuEntity.setWaichuTypes(Integer.valueOf(data.get(0)));   //交通工具 要改的
//                            waichuEntity.setWaichuMudidi(data.get(0));                    //去哪里 要改的
//                            waichuEntity.setWaichuChufadi(data.get(0));                    //地址 要改的
//                            waichuEntity.setWaichuShentiTypes(Integer.valueOf(data.get(0)));   //身体状态 要改的
//                            waichuEntity.setWaichuChufaTime(sdf.parse(data.get(0)));          //出发时间 要改的
//                            waichuEntity.setWaichuDaodaTime(sdf.parse(data.get(0)));          //到达时间 要改的
//                            waichuEntity.setWaichuYesnoTypes(Integer.valueOf(data.get(0)));   //申报状态 要改的
//                            waichuEntity.setWaichuYesnoText(data.get(0));                    //审核回复 要改的
//                            waichuEntity.setWaichuShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            waichuEntity.setInsertTime(date);//时间
//                            waichuEntity.setCreateTime(date);//时间
                            waichuList.add(waichuEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        waichuService.insertBatch(waichuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = waichuService.queryPage(params);

        //字典表数据转换
        List<WaichuView> list =(List<WaichuView>)page.getList();
        for(WaichuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WaichuEntity waichu = waichuService.selectById(id);
            if(waichu !=null){


                //entity转view
                WaichuView view = new WaichuView();
                BeanUtils.copyProperties( waichu , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(waichu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(waichu.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody WaichuEntity waichu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,waichu:{}",this.getClass().getName(),waichu.toString());
        Wrapper<WaichuEntity> queryWrapper = new EntityWrapper<WaichuEntity>()
            .eq("yonghu_id", waichu.getYonghuId())
            .eq("laoshi_id", waichu.getLaoshiId())
            .eq("waichu_text", waichu.getWaichuText())
            .eq("waichu_types", waichu.getWaichuTypes())
            .eq("waichu_mudidi", waichu.getWaichuMudidi())
            .eq("waichu_chufadi", waichu.getWaichuChufadi())
            .eq("waichu_shenti_types", waichu.getWaichuShentiTypes())
            .in("waichu_yesno_types", new Integer[]{1,2})
            .eq("waichu_yesno_text", waichu.getWaichuYesnoText())
//            .notIn("waichu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WaichuEntity waichuEntity = waichuService.selectOne(queryWrapper);
        if(waichuEntity==null){
            waichu.setWaichuYesnoTypes(1);
            waichu.setInsertTime(new Date());
            waichu.setCreateTime(new Date());
        waichuService.insert(waichu);

            return R.ok();
        }else {
            if(waichuEntity.getWaichuYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(waichuEntity.getWaichuYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

