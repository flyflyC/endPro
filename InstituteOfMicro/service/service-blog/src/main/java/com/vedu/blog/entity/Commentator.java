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
    @TableName("v_commentator")
@ApiModel(value="Commentator对象", description="")
public class Commentator implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ID_WORKER_STR)
      private String id;

      @ApiModelProperty(value = "用户id")
      private String memberId;

      @ApiModelProperty(value = "博客id")
      private String blogId;

      @ApiModelProperty(value = "内容")
      private String content;

      @ApiModelProperty(value = "用户昵称")
      private String cname;

      @ApiModelProperty(value = "会员头像")
      private String avatar;

      @ApiModelProperty(value = "状态")
      private Integer status;

      @ApiModelProperty(value = "浏览数")
      private Integer views;

      @ApiModelProperty(value = "点赞数")
      private Integer goods;

      @ApiModelProperty(value = "评论")
      private Integer comments;

      @ApiModelProperty(value = "插入时间")
      @TableField(fill = FieldFill.INSERT)
      private Date gmtCreate;

      @ApiModelProperty(value = "更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Date gmtModified;


}
