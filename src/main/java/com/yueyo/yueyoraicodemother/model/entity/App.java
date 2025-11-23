package com.yueyo.yueyoraicodemother.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * app 实体类。
 *
 * @author <a href="https://github.com/paliceHu">yueyor</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("app")
public class App implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;


    /**
     * app名称
     */
    @Column("appName")
    private String appName;

    /**
     * app封面
     */
    private String cover;

    /**
     * app初始化的prompt
     */
    @Column("initPrompt")
    private String initPrompt;

    /**
     * 代码生成方式
     */
    @Column("codeGenType")
    private String codeGenType;

    /**
     * 部署标识
     */
    @Column("deployKey")
    private String deployKey;

    /**
     * 部署时间
     */
    @Column("deployTime")
    private LocalDateTime deployTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 用户id
     */
    @Column("userId")
    private Long userId;

    /**
     * 编辑时间
     */
    @Column("editTime")
    private LocalDateTime editTime;

    /**
     * 创建时间
     */
    @Column("createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("updateTime")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(value = "isDelete", isLogicDelete = true)
    private Integer isDelete;

}
