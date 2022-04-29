package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService service;

    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto dto){
        ApiResponse apiResponse = service.addComment(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_MY_COMMENT')")
    @PutMapping
    public HttpEntity<?> editComment(@RequestBody CommentDto dto, @PathVariable Long id){
        ApiResponse apiResponse = service.editComment(dto,id);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_MY_COMMENT','DELETE_COMMENT')")
    @DeleteMapping
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        ApiResponse apiResponse = service.deleteComment(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    HttpEntity<?> getComments(){
        List<Comment> comments = service.getComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public HttpEntity<Comment> getComment(@PathVariable Long id){
        Comment comment = service.getComment(id);
        return ResponseEntity.ok(comment);
    }
}
