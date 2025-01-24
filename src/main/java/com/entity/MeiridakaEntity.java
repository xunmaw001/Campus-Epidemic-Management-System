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
 * 每日打卡
 *
 * @author 
 * @email
 */
@TableName("meiridaka")
public class MeiridakaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public MeiridakaEntity() {

	}

	public MeiridakaEntity(T t) {
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
     * 老师
     */
    @ColumnInfo(comment="老师",type="int(11)")
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 健康码打卡
     */
    @ColumnInfo(comment="健康码打卡",type="varchar(200)")
    @TableField(value = "meiridaka_name")

    private String meiridakaName;


    /**
     * 健康码照片
     */
    @ColumnInfo(comment="健康码照片",type="varchar(200)")
    @TableField(value = "meiridaka_file")

    private String meiridakaFile;


    /**
     * 体温
     */
    @ColumnInfo(comment="体温",type="decimal(10,2)")
    @TableField(value = "meiridaka_wendu")

    private Double meiridakaWendu;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="text")
    @TableField(value = "meiridaka_text")

    private String meiridakaText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "meiridaka_delete")

    private Integer meiridakaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：健康码打卡
	 */
    public String getMeiridakaName() {
        return meiridakaName;
    }
    /**
	 * 设置：健康码打卡
	 */

    public void setMeiridakaName(String meiridakaName) {
        this.meiridakaName = meiridakaName;
    }
    /**
	 * 获取：健康码照片
	 */
    public String getMeiridakaFile() {
        return meiridakaFile;
    }
    /**
	 * 设置：健康码照片
	 */

    public void setMeiridakaFile(String meiridakaFile) {
        this.meiridakaFile = meiridakaFile;
    }
    /**
	 * 获取：体温
	 */
    public Double getMeiridakaWendu() {
        return meiridakaWendu;
    }
    /**
	 * 设置：体温
	 */

    public void setMeiridakaWendu(Double meiridakaWendu) {
        this.meiridakaWendu = meiridakaWendu;
    }
    /**
	 * 获取：备注
	 */
    public String getMeiridakaText() {
        return meiridakaText;
    }
    /**
	 * 设置：备注
	 */

    public void setMeiridakaText(String meiridakaText) {
        this.meiridakaText = meiridakaText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getMeiridakaDelete() {
        return meiridakaDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setMeiridakaDelete(Integer meiridakaDelete) {
        this.meiridakaDelete = meiridakaDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Meiridaka{" +
            ", id=" + id +
            ", laoshiId=" + laoshiId +
            ", meiridakaName=" + meiridakaName +
            ", meiridakaFile=" + meiridakaFile +
            ", meiridakaWendu=" + meiridakaWendu +
            ", meiridakaText=" + meiridakaText +
            ", meiridakaDelete=" + meiridakaDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
