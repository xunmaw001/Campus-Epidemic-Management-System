
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
 * 每日打卡
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/meiridaka")
public class MeiridakaController {
    private static final Logger logger = LoggerFactory.getLogger(MeiridakaController.class);

    private static final String TABLE_NAME = "meiridaka";

    @Autowired
    private MeiridakaService meiridakaService;


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
    private QingjiaService qingjiaService;//请假
    @Autowired
    private WaichuService waichuService;//外出信息
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
        params.put("meiridakaDeleteStart",1);params.put("meiridakaDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = meiridakaService.queryPage(params);

        //字典表数据转换
        List<MeiridakaView> list =(List<MeiridakaView>)page.getList();
        for(MeiridakaView c:list){
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
        MeiridakaEntity meiridaka = meiridakaService.selectById(id);
        if(meiridaka !=null){
            //entity转view
            MeiridakaView view = new MeiridakaView();
            BeanUtils.copyProperties( meiridaka , view );//把实体数据重构到view中
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(meiridaka.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody MeiridakaEntity meiridaka, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,meiridaka:{}",this.getClass().getName(),meiridaka.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老师".equals(role))
            meiridaka.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<MeiridakaEntity> queryWrapper = new EntityWrapper<MeiridakaEntity>()
            .eq("laoshi_id", meiridaka.getLaoshiId())
            .eq("meiridaka_name", meiridaka.getMeiridakaName())
            .eq("meiridaka_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MeiridakaEntity meiridakaEntity = meiridakaService.selectOne(queryWrapper);
        if(meiridakaEntity==null){
            meiridaka.setMeiridakaDelete(1);
            meiridaka.setInsertTime(new Date());
            meiridaka.setCreateTime(new Date());
            meiridakaService.insert(meiridaka);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody MeiridakaEntity meiridaka, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,meiridaka:{}",this.getClass().getName(),meiridaka.toString());
        MeiridakaEntity oldMeiridakaEntity = meiridakaService.selectById(meiridaka.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老师".equals(role))
//            meiridaka.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(meiridaka.getMeiridakaFile()) || "null".equals(meiridaka.getMeiridakaFile())){
                meiridaka.setMeiridakaFile(null);
        }
        if("".equals(meiridaka.getMeiridakaText()) || "null".equals(meiridaka.getMeiridakaText())){
                meiridaka.setMeiridakaText(null);
        }

            meiridakaService.updateById(meiridaka);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<MeiridakaEntity> oldMeiridakaList =meiridakaService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<MeiridakaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            MeiridakaEntity meiridakaEntity = new MeiridakaEntity();
            meiridakaEntity.setId(id);
            meiridakaEntity.setMeiridakaDelete(2);
            list.add(meiridakaEntity);
        }
        if(list != null && list.size() >0){
            meiridakaService.updateBatchById(list);
        }

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
            List<MeiridakaEntity> meiridakaList = new ArrayList<>();//上传的东西
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
                            MeiridakaEntity meiridakaEntity = new MeiridakaEntity();
//                            meiridakaEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            meiridakaEntity.setMeiridakaName(data.get(0));                    //健康码打卡 要改的
//                            meiridakaEntity.setMeiridakaFile(data.get(0));                    //健康码照片 要改的
//                            meiridakaEntity.setMeiridakaWendu(data.get(0));                    //体温 要改的
//                            meiridakaEntity.setMeiridakaText(data.get(0));                    //备注 要改的
//                            meiridakaEntity.setMeiridakaDelete(1);//逻辑删除字段
//                            meiridakaEntity.setInsertTime(date);//时间
//                            meiridakaEntity.setCreateTime(date);//时间
                            meiridakaList.add(meiridakaEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        meiridakaService.insertBatch(meiridakaList);
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
        PageUtils page = meiridakaService.queryPage(params);

        //字典表数据转换
        List<MeiridakaView> list =(List<MeiridakaView>)page.getList();
        for(MeiridakaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MeiridakaEntity meiridaka = meiridakaService.selectById(id);
            if(meiridaka !=null){


                //entity转view
                MeiridakaView view = new MeiridakaView();
                BeanUtils.copyProperties( meiridaka , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(meiridaka.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
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
    public R add(@RequestBody MeiridakaEntity meiridaka, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,meiridaka:{}",this.getClass().getName(),meiridaka.toString());
        Wrapper<MeiridakaEntity> queryWrapper = new EntityWrapper<MeiridakaEntity>()
            .eq("laoshi_id", meiridaka.getLaoshiId())
            .eq("meiridaka_name", meiridaka.getMeiridakaName())
            .eq("meiridaka_text", meiridaka.getMeiridakaText())
            .eq("meiridaka_delete", meiridaka.getMeiridakaDelete())
//            .notIn("meiridaka_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MeiridakaEntity meiridakaEntity = meiridakaService.selectOne(queryWrapper);
        if(meiridakaEntity==null){
            meiridaka.setMeiridakaDelete(1);
            meiridaka.setInsertTime(new Date());
            meiridaka.setCreateTime(new Date());
        meiridakaService.insert(meiridaka);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

