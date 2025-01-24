package com.entity.vo;

import com.entity.MeiridakaEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 每日打卡
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("meiridaka")
public class MeiridakaVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 老师
     */

    @TableField(value = "laoshi_id")
    private Integer laoshiId;


    /**
     * 健康码打卡
     */

    @TableField(value = "meiridaka_name")
    private String meiridakaName;


    /**
     * 健康码照片
     */

    @TableField(value = "meiridaka_file")
    private String meiridakaFile;


    /**
     * 体温
     */

    @TableField(value = "meiridaka_wendu")
    private Double meiridakaWendu;


    /**
     * 备注
     */

    @TableField(value = "meiridaka_text")
    private String meiridakaText;


    /**
     * 逻辑删除
     */

    @TableField(value = "meiridaka_delete")
    private Integer meiridakaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
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
	 * 设置：健康码打卡
	 */
    public String getMeiridakaName() {
        return meiridakaName;
    }


    /**
	 * 获取：健康码打卡
	 */

    public void setMeiridakaName(String meiridakaName) {
        this.meiridakaName = meiridakaName;
    }
    /**
	 * 设置：健康码照片
	 */
    public String getMeiridakaFile() {
        return meiridakaFile;
    }


    /**
	 * 获取：健康码照片
	 */

    public void setMeiridakaFile(String meiridakaFile) {
        this.meiridakaFile = meiridakaFile;
    }
    /**
	 * 设置：体温
	 */
    public Double getMeiridakaWendu() {
        return meiridakaWendu;
    }


    /**
	 * 获取：体温
	 */

    public void setMeiridakaWendu(Double meiridakaWendu) {
        this.meiridakaWendu = meiridakaWendu;
    }
    /**
	 * 设置：备注
	 */
    public String getMeiridakaText() {
        return meiridakaText;
    }


    /**
	 * 获取：备注
	 */

    public void setMeiridakaText(String meiridakaText) {
        this.meiridakaText = meiridakaText;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getMeiridakaDelete() {
        return meiridakaDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setMeiridakaDelete(Integer meiridakaDelete) {
        this.meiridakaDelete = meiridakaDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
