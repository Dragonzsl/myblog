package xyz.myzsl.myblog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName articles
 */
@TableName(value ="articles")
@Data
public class Article implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String author;

    /**
     * 
     */
    private String category;

    /**
     * 
     */
    private String tabloid;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String tags;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private Integer views;

    /**
     * 
     */
    private Long gmtCreate;

    /**
     * 
     */
    private Long gmtUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}