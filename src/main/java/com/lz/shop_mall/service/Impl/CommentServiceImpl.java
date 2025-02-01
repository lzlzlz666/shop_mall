package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.CommentMapper;
import com.lz.shop_mall.mapper.LikeCommentMapper;
import com.lz.shop_mall.mapper.UserMapper;
import com.lz.shop_mall.pojo.Comment;
import com.lz.shop_mall.pojo.LikeComment;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.dto.CommentDTO;
import com.lz.shop_mall.pojo.dto.UserDTO;
import com.lz.shop_mall.service.CommentService;
import com.lz.shop_mall.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LikeCommentMapper likeCommentMapper;

    /**
     * 发送评论
     * @param comment
     * @return
     */
    public Result sendComment(Comment comment) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        commentMapper.insert(comment, userId);
        return Result.success("发送评论成功！");
    }

    /**
     * 获取所有的评论信息
     * @return
     */
    public Result<List<CommentDTO>> getComments(Integer productId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Comment> commentList = commentMapper.getComments(productId);

        // 创建一个新的 List 用来存放转换后的 CommentDTO 列表
        List<CommentDTO> commentDTOList = new ArrayList<>();

        // 遍历评论列表，将每个 Comment 转换为 CommentDTO
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();

            Integer commentId = comment.getCommentId();
            UserDTO sendUser = userMapper.getUserLogin(userId);

            // 判断登录的用户是否对这篇文章进行点赞
            LikeComment likeComment = likeCommentMapper.getLikeCommentByUserId(userId, commentId);

            if (likeComment == null) {
                commentDTO.setIsLike(0);
            } else {
                if (likeComment.getLikeState() == 0){
                    commentDTO.setIsLike(0);
                } else {
                    commentDTO.setIsLike(1);
                }
            }

            // 设置 CommentDTO 的属性
            commentDTO.setCommentId(comment.getCommentId());
            commentDTO.setProductId(productId);
            commentDTO.setContent(comment.getContent());
            commentDTO.setUserId(comment.getUserId());
            commentDTO.setUsername(sendUser.getUsername());
            commentDTO.setUserPic(sendUser.getUserPic());
            commentDTO.setLike(comment.getLike());
            commentDTO.setPid(comment.getPid());
            commentDTO.setTargetUserId(comment.getTargetUserId());
            commentDTO.setTarget(null);
            commentDTO.setCreateTime(comment.getCreateTime());

            // 如果需要 List<Comment> 参数，可以在这里设置
            List<Comment> TempLC = commentMapper.getSonComments(comment.getCommentId());
            for (Comment comment1 : TempLC) {
                Integer userId1 = comment1.getUserId();
                Integer targetUserId1 = comment1.getTargetUserId();

                UserDTO sendUser1 = userMapper.getUserLogin(userId1);
                UserDTO targetUser1 = userMapper.getUserLogin(targetUserId1);

                Integer commentId1 = comment1.getCommentId();
                LikeComment likeComment1 = likeCommentMapper.getLikeCommentByUserId(userId, commentId1);

                if (likeComment1 == null) {
                    comment1.setIsLike(0);
                } else {
                    if (likeComment1.getLikeState() == 0){
                        comment1.setIsLike(0);
                    } else {
                        comment1.setIsLike(1);
                    }
                }

                comment1.setUsername(sendUser1.getUsername());
                comment1.setUserPic(sendUser1.getUserPic());
                comment1.setTarget(targetUser1.getUsername());
            }
            commentDTO.setListComments(TempLC);

            // 将 CommentDTO 添加到结果列表中
            commentDTOList.add(commentDTO);
        }

        return Result.success(commentDTOList);
    }

    /**
     * 获取一条指定id的评论
     * @param id
     * @return
     */
    public Result<Comment> getComment(Integer id) {
        Comment comment = commentMapper.getComment(id);

        UserDTO sendUser = userMapper.getUserLogin(comment.getUserId());

        Integer targetUserId = comment.getTargetUserId();
        if(targetUserId != null) {
            UserDTO targetUser = userMapper.getUserLogin(targetUserId);
            comment.setTarget(targetUser.getUsername());
        }

        comment.setUsername(sendUser.getUsername());
        comment.setUserPic(sendUser.getUserPic());

        // 判断登录的用户是否对这篇文章进行点赞
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        Integer commentId = comment.getCommentId();
        LikeComment likeComment = likeCommentMapper.getLikeCommentByUserId(userId, commentId);

        if (likeComment == null) {
            comment.setIsLike(0);
        } else {
            if (likeComment.getLikeState() == 0){
                comment.setIsLike(0);
            } else {
                comment.setIsLike(1);
            }
        }

        if (comment == null) {
            return Result.error("未找到指定的评论");
        }
        return Result.success(comment);
    }

    /**
     * 0为取消点赞，1为点赞
     * @param likeState
     * @return
     */
    public Result thumbUp(int commentId, int likeState) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        LikeComment likeComment = likeCommentMapper.getLikeCommentByUserId(userId, commentId);
        if (likeComment == null) {
            likeCommentMapper.insert(commentId, likeState, userId);
            commentMapper.updateLike(commentId);
        } else {
            likeCommentMapper.update(commentId, likeState, userId);
            commentMapper.updateLikeByLikeState(commentId, likeState);
        }

        return Result.success("更改点赞信息成功!");
    }
}
