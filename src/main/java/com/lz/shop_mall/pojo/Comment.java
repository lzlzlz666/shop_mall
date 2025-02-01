package com.lz.shop_mall.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    // 评论ID，主键，自动递增
    private Integer commentId;
    // 关联产品ID（业务模块ID）
    private Integer productId;
    // 评论内容
    private String content;
    // 用户名
    private String username;
    // 用户头像
    private String userPic;
    // 用户ID
    private Integer userId;
    // 点赞数
    private Integer like;
    // 判断当前登录用户是否点赞    0未点赞, 1是点赞
    private Integer isLike;
    // 父级评论ID（用于回复评论）
    private Integer pid;
    // 被回复的目标用户ID
    private Integer targetUserId;
    // 回复的目标（用户名）
    private String target;
    // 评论创建时间
    private LocalDateTime createTime;
}
