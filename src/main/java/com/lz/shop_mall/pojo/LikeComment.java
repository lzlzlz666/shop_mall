package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeComment {
    private Integer likeCommentId; // like_comment_id, 主键
    private Integer userId;        // user_id, 关联用户外键
    private Integer commentId;     // comment_id, 关联评论外键
    private Integer likeState;      // like_state, 点赞状态(0是取消点赞, 1是已经点赞)
    private LocalDateTime createTime;       // create_time, 创建时间
    private LocalDateTime updateTime;       // update_time, 更新时间
}
