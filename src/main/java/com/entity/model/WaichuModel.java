package com.entity.model;

import com.entity.WaichuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 外出信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WaichuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学生
     */
    private Integer yonghuId;


    /**
     * 老师
     */
    private Integer laoshiId;


    /**
     * 申请理由
     */
    private String waichuText;


    /**
     * 交通工具
     */
    private Integer waichuTypes;


    /**
     * 去哪里
     */
    private String waichuMudidi;


    /**
     * 地址
     */
    private String waichuChufadi;


    /**
     * 身体状态
     */
    private Integer waichuShentiTypes;


    /**
     * 出发时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date waichuChufaTime;


    /**
     * 到达时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date waichuDaodaTime;


    /**
     * 申报状态
     */
    private Integer waichuYesnoTypes;


    /**
     * 审核回复
     */
    private String waichuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date waichuShenheTime;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：学生
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 设置：老师
	 */
    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 获取：申请理由
	 */
    public String getWaichuText() {
        return waichuText;
    }


    /**
	 * 设置：申请理由
	 */
    public void setWaichuText(String waichuText) {
        this.waichuText = waichuText;
    }
    /**
	 * 获取：交通工具
	 */
    public Integer getWaichuTypes() {
        return waichuTypes;
    }


    /**
	 * 设置：交通工具
	 */
    public void setWaichuTypes(Integer waichuTypes) {
        this.waichuTypes = waichuTypes;
    }
    /**
	 * 获取：去哪里
	 */
    public String getWaichuMudidi() {
        return waichuMudidi;
    }


    /**
	 * 设置：去哪里
	 */
    public void setWaichuMudidi(String waichuMudidi) {
        this.waichuMudidi = waichuMudidi;
    }
    /**
	 * 获取：地址
	 */
    public String getWaichuChufadi() {
        return waichuChufadi;
    }


    /**
	 * 设置：地址
	 */
    public void setWaichuChufadi(String waichuChufadi) {
        this.waichuChufadi = waichuChufadi;
    }
    /**
	 * 获取：身体状态
	 */
    public Integer getWaichuShentiTypes() {
        return waichuShentiTypes;
    }


    /**
	 * 设置：身体状态
	 */
    public void setWaichuShentiTypes(Integer waichuShentiTypes) {
        this.waichuShentiTypes = waichuShentiTypes;
    }
    /**
	 * 获取：出发时间
	 */
    public Date getWaichuChufaTime() {
        return waichuChufaTime;
    }


    /**
	 * 设置：出发时间
	 */
    public void setWaichuChufaTime(Date waichuChufaTime) {
        this.waichuChufaTime = waichuChufaTime;
    }
    /**
	 * 获取：到达时间
	 */
    public Date getWaichuDaodaTime() {
        return waichuDaodaTime;
    }


    /**
	 * 设置：到达时间
	 */
    public void setWaichuDaodaTime(Date waichuDaodaTime) {
        this.waichuDaodaTime = waichuDaodaTime;
    }
    /**
	 * 获取：申报状态
	 */
    public Integer getWaichuYesnoTypes() {
        return waichuYesnoTypes;
    }


    /**
	 * 设置：申报状态
	 */
    public void setWaichuYesnoTypes(Integer waichuYesnoTypes) {
        this.waichuYesnoTypes = waichuYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getWaichuYesnoText() {
        return waichuYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setWaichuYesnoText(String waichuYesnoText) {
        this.waichuYesnoText = waichuYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getWaichuShenheTime() {
        return waichuShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setWaichuShenheTime(Date waichuShenheTime) {
        this.waichuShenheTime = waichuShenheTime;
    }
    /**
	 * 获取：活动报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：活动报名时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
