package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 外出信息
 *
 * @author 
 * @email
 */
@TableName("waichu")
public class WaichuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WaichuEntity() {

	}

	public WaichuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 学生
     */
    @ColumnInfo(comment="学生",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 老师
     */
    @ColumnInfo(comment="老师",type="int(11)")
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 申请理由
     */
    @ColumnInfo(comment="申请理由",type="text")
    @TableField(value = "waichu_text")

    private String waichuText;


    /**
     * 交通工具
     */
    @ColumnInfo(comment="交通工具",type="int(11)")
    @TableField(value = "waichu_types")

    private Integer waichuTypes;


    /**
     * 去哪里
     */
    @ColumnInfo(comment="去哪里",type="varchar(200)")
    @TableField(value = "waichu_mudidi")

    private String waichuMudidi;


    /**
     * 地址
     */
    @ColumnInfo(comment="地址",type="varchar(200)")
    @TableField(value = "waichu_chufadi")

    private String waichuChufadi;


    /**
     * 身体状态
     */
    @ColumnInfo(comment="身体状态",type="int(11)")
    @TableField(value = "waichu_shenti_types")

    private Integer waichuShentiTypes;


    /**
     * 出发时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="出发时间",type="date")
    @TableField(value = "waichu_chufa_time")

    private Date waichuChufaTime;


    /**
     * 到达时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="到达时间",type="date")
    @TableField(value = "waichu_daoda_time")

    private Date waichuDaodaTime;


    /**
     * 申报状态
     */
    @ColumnInfo(comment="申报状态",type="int(11)")
    @TableField(value = "waichu_yesno_types")

    private Integer waichuYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="text")
    @TableField(value = "waichu_yesno_text")

    private String waichuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "waichu_shenhe_time")

    private Date waichuShenheTime;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="活动报名时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Waichu{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", laoshiId=" + laoshiId +
            ", waichuText=" + waichuText +
            ", waichuTypes=" + waichuTypes +
            ", waichuMudidi=" + waichuMudidi +
            ", waichuChufadi=" + waichuChufadi +
            ", waichuShentiTypes=" + waichuShentiTypes +
            ", waichuChufaTime=" + DateUtil.convertString(waichuChufaTime,"yyyy-MM-dd") +
            ", waichuDaodaTime=" + DateUtil.convertString(waichuDaodaTime,"yyyy-MM-dd") +
            ", waichuYesnoTypes=" + waichuYesnoTypes +
            ", waichuYesnoText=" + waichuYesnoText +
            ", waichuShenheTime=" + DateUtil.convertString(waichuShenheTime,"yyyy-MM-dd") +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
