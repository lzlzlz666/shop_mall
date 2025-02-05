package com.lz.shop_mall.controller;

import com.lz.shop_mall.pojo.Comment;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.dto.CommentDTO;
import com.lz.shop_mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 发送评论
    @PostMapping("/send")
    public Result sendComment(@RequestBody Comment comment) {
        return commentService.sendComment(comment);
    }

    @GetMapping("/allComments")
    public Result<List<CommentDTO>> getComments(@RequestParam int productId) {
        return commentService.getComments(productId);
    }

    // 未登录用户只能看两条评论(含子评论)
    @GetMapping()
    public Result<List<CommentDTO>> getTwoComments(@RequestParam int productId) {
        return commentService.getTwoComments(productId);
    }

    @GetMapping("/{id}")
    public Result<Comment> getComment(@PathVariable("id") Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping("/like")
    public Result thumbUp(@RequestParam int commentId,
                          @RequestParam int likeState) {
        return commentService.thumbUp(commentId, likeState);
    }
}
