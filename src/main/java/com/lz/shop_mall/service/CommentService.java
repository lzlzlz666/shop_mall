package com.lz.shop_mall.service;

import com.lz.shop_mall.pojo.Comment;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Result sendComment(Comment comment);

    Result<List<CommentDTO>> getComments(Integer productId);

    Result<Comment> getComment(Integer id);

    Result thumbUp(int commentId, int likeState);

    Result<List<CommentDTO>> getTwoComments(int productId);
}
