package com.entity.vo;

import com.entity.WaichuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 外出信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("waichu")
public class WaichuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学生
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 老师
     */

    @TableField(value = "laoshi_id")
    private Integer laoshiId;


    /**
     * 申请理由
     */

    @TableField(value = "waichu_text")
    private String waichuText;


    /**
     * 交通工具
     */

    @TableField(value = "waichu_types")
    private Integer waichuTypes;


    /**
     * 去哪里
     */

    @TableField(value = "waichu_mudidi")
    private String waichuMudidi;


    /**
     * 地址
     */

    @TableField(value = "waichu_chufadi")
    private String waichuChufadi;


    /**
     * 身体状态
     */

    @TableField(value = "waichu_shenti_types")
    private Integer waichuShentiTypes;


    /**
     * 出发时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "waichu_chufa_time")
    private Date waichuChufaTime;


    /**
     * 到达时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "waichu_daoda_time")
    private Date waichuDaodaTime;


    /**
     * 申报状态
     */

    @TableField(value = "waichu_yesno_types")
    private Integer waichuYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "waichu_yesno_text")
    private String waichuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "waichu_shenhe_time")
    private Date waichuShenheTime;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：学生
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 获取：老师
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 设置：申请理由
	 */
    public String getWaichuText() {
        return waichuText;
    }


    /**
	 * 获取：申请理由
	 */

    public void setWaichuText(String waichuText) {
        this.waichuText = waichuText;
    }
    /**
	 * 设置：交通工具
	 */
    public Integer getWaichuTypes() {
        return waichuTypes;
    }


    /**
	 * 获取：交通工具
	 */

    public void setWaichuTypes(Integer waichuTypes) {
        this.waichuTypes = waichuTypes;
    }
    /**
	 * 设置：去哪里
	 */
    public String getWaichuMudidi() {
        return waichuMudidi;
    }


    /**
	 * 获取：去哪里
	 */

    public void setWaichuMudidi(String waichuMudidi) {
        this.waichuMudidi = waichuMudidi;
    }
    /**
	 * 设置：地址
	 */
    public String getWaichuChufadi() {
        return waichuChufadi;
    }


    /**
	 * 获取：地址
	 */

    public void setWaichuChufadi(String waichuChufadi) {
        this.waichuChufadi = waichuChufadi;
    }
    /**
	 * 设置：身体状态
	 */
    public Integer getWaichuShentiTypes() {
        return waichuShentiTypes;
    }


    /**
	 * 获取：身体状态
	 */

    public void setWaichuShentiTypes(Integer waichuShentiTypes) {
        this.waichuShentiTypes = waichuShentiTypes;
    }
    /**
	 * 设置：出发时间
	 */
    public Date getWaichuChufaTime() {
        return waichuChufaTime;
    }


    /**
	 * 获取：出发时间
	 */

    public void setWaichuChufaTime(Date waichuChufaTime) {
        this.waichuChufaTime = waichuChufaTime;
    }
    /**
	 * 设置：到达时间
	 */
    public Date getWaichuDaodaTime() {
        return waichuDaodaTime;
    }


    /**
	 * 获取：到达时间
	 */

    public void setWaichuDaodaTime(Date waichuDaodaTime) {
        this.waichuDaodaTime = waichuDaodaTime;
    }
    /**
	 * 设置：申报状态
	 */
    public Integer getWaichuYesnoTypes() {
        return waichuYesnoTypes;
    }


    /**
	 * 获取：申报状态
	 */

    public void setWaichuYesnoTypes(Integer waichuYesnoTypes) {
        this.waichuYesnoTypes = waichuYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getWaichuYesnoText() {
        return waichuYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setWaichuYesnoText(String waichuYesnoText) {
        this.waichuYesnoText = waichuYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getWaichuShenheTime() {
        return waichuShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setWaichuShenheTime(Date waichuShenheTime) {
        this.waichuShenheTime = waichuShenheTime;
    }
    /**
	 * 设置：活动报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：活动报名时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
