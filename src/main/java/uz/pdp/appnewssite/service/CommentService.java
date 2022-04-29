package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    public ApiResponse addComment(CommentDto dto) {
        Optional<Post> optional = postRepository.findById(dto.getPostId());
        if (!optional.isPresent()) {
            return new ApiResponse("Not Found", false);
        }
        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setPost(optional.get());
        commentRepository.save(comment);
        return new ApiResponse("Saqlandi", true);
    }

    public ApiResponse editComment(CommentDto dto, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            return new ApiResponse("Not Found", false);
        }

        Optional<Post> optional = postRepository.findById(dto.getPostId());
        if (!optional.isPresent()) {
            return new ApiResponse("Not Found", false);
        }

        Comment comment = optionalComment.get();
        comment.setText(dto.getText());
        comment.setPost(optional.get());
        commentRepository.save(comment);
        return new ApiResponse("Saqlandi", true);
    }

    public ApiResponse deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            return new ApiResponse("Not Found", false);
        }
        commentRepository.delete(optionalComment.get());
        return new ApiResponse("O'chirildi", true);
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public Comment getComment(Long id){
        Optional<Comment> optional = commentRepository.findById(id);
        return optional.orElseGet(Comment::new);
    }


}
