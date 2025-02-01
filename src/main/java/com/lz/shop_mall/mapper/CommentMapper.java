package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 插入评论，注意 Comment 对象中的属性将与表字段进行映射
    @Insert("INSERT INTO comment (product_id, content, user_id, `like`, pid, target_user_id, create_time) " +
            "VALUES (#{comment.productId}, #{comment.content}, #{userId}, #{comment.like}, #{comment.pid}, #{comment.targetUserId}, NOW())")
    void insert(@Param("comment") Comment comment, @Param("userId") Integer userId);

    // 获取父级评论
    @Select("SELECT * FROM comment WHERE product_id = #{productId} AND pid IS NULL")
    List<Comment> getComments(Integer productId);

    @Select("select * from comment where pid = #{commentId} and pid is not null")
    List<Comment> getSonComments(Integer commentId);

    @Select("select * from comment where comment_id = #{id}")
    Comment getComment(Integer id);

    @Update("UPDATE comment SET `like` = `like` + 1 WHERE comment_id = #{commentId}")
    void updateLike(int commentId);

    void updateLikeByLikeState(int commentId, int likeState);

}
