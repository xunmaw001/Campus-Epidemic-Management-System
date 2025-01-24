package com.entity.model;

import com.entity.MeiridakaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 每日打卡
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class MeiridakaModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 老师
     */
    private Integer laoshiId;


    /**
     * 健康码打卡
     */
    private String meiridakaName;


    /**
     * 健康码照片
     */
    private String meiridakaFile;


    /**
     * 体温
     */
    private Double meiridakaWendu;


    /**
     * 备注
     */
    private String meiridakaText;


    /**
     * 逻辑删除
     */
    private Integer meiridakaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
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

    }
