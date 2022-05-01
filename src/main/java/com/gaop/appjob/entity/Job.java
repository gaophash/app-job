package com.gaop.appjob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职位信息
     */
    private String jobName;

    /**
     * 最小薪资
     */
    private Integer payMin;

    /**
     * 最大薪资
     */
    private Integer payMax;

    /**
     * 一年机薪
     */
    private Integer payMonth;

    /**
     * 福利待遇
     */
    private String welfare;

    /**
     * 学历信息
     */
    private String education;

    /**
     * 工作年限
     */
    private String workingYears;

    /**
     * 发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime publishTime;

    /**
     * 工作地点
     */
    private String workPosition;

    /**
     * 公司名称(冗余字段)
     */
    private String companyName;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 区县id
     */
    private Integer areaId;


}
