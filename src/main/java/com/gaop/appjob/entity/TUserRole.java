package com.gaop.appjob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户唯一userId
     */
    private String userId;

    private Long roleId;

    private Integer status;

    private LocalDateTime createTime;

    private Date updateTime;


}
