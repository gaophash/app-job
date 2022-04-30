package com.gaop.appjob.entity;

import java.util.Date;
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
public class TRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    private Integer status;

    private LocalDateTime createTime;

    private Date updateTime;


}
