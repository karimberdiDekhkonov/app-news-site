package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService service;

    @PostMapping
    public HttpEntity<?> addPost(@RequestBody Post post){
        ApiResponse apiResponse = service.addPost(post);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> editPost(@RequestBody Post post,@PathVariable Long id){
        ApiResponse apiResponse = service.editPost(post,id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getPosts(){
        List<Post> list = service.getPosts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPost(@PathVariable Long id){
        Post post = service.getPost(id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping
    public HttpEntity<?> addPost(@PathVariable Long id){
        ApiResponse apiResponse = service.deletePost(id);
        return ResponseEntity.ok(apiResponse);
    }
}
