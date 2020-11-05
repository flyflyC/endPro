package com.vedu.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("v_blog")
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ID_WORKER_STR)
      private String id;

      @ApiModelProperty(value = "作者")
      private String username;

      @ApiModelProperty(value = "用户id")
      private String memberId;

      @ApiModelProperty(value = "标题")
      private String title;

      @ApiModelProperty(value = "简介")
      private String description;

      @ApiModelProperty(value = "正文")
      private String content;

      @ApiModelProperty(value = "状态：0表示发布，1表示未发布")
      private Integer status;

      @ApiModelProperty(value = "观看数")
      private Integer views;

      @ApiModelProperty(value = "点赞数")
      private Integer goods;

      @ApiModelProperty(value = "评论")
      private Integer comments;

      @TableField(fill = FieldFill.INSERT)
      @ApiModelProperty(value = "创建时间")
      private Date gmtCreate;

      @TableField(fill = FieldFill.INSERT_UPDATE)
      @ApiModelProperty(value = "删除时间")
      private Date gmtModified;


}
