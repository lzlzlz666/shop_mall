package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.LikeComment;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeCommentMapper {

    @Select("select * from like_comment where user_id = #{userId} and comment_id = #{commentId}")
    LikeComment getLikeCommentByUserId(Integer userId, int commentId);

    @Insert("INSERT INTO like_comment (comment_id, like_state, user_id, create_time, update_time) VALUES (#{commentId}, #{likeState}, #{userId}, NOW(), NOW())")
    void insert(@Param("commentId") int commentId, @Param("likeState") int likeState, @Param("userId") Integer userId);

    @Update("UPDATE like_comment SET like_state = #{likeState}, update_time = NOW() " +
            "WHERE comment_id = #{commentId} AND user_id = #{userId}")
    void update( @Param("commentId") int commentId, @Param("likeState") int likeState, @Param("userId") Integer userId);

}
