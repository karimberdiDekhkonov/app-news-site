package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    public ApiResponse addPost(Post post){
        if (postRepository.existsByTitle(post.getTitle())){
            return new ApiResponse("This Title Is Already Exist",false);
        }
        postRepository.save(post);
        return new ApiResponse("Saqlandi",true);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    public ApiResponse editPost(Post post,Long id){
        Optional<Post> optional = postRepository.findById(id);
        if (!optional.isPresent()){
            return new ApiResponse("Id is not found",false);
        }
        Post editing = new Post(
                post.getTitle(),
                post.getText(),
                post.getUrl()
        );
        postRepository.save(editing);
        return new ApiResponse("Saqlandi",true);
    }

    public List<Post>getPosts(){
        return postRepository.findAll();
    }

    public Post getPost(Long id){
        Optional<Post> optional = postRepository.findById(id);
        return optional.orElseGet(Post::new);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    public ApiResponse deletePost(Long id){
        Optional<Post> optional = postRepository.findById(id);
        if (!optional.isPresent()){
            return new ApiResponse("post topilmadi",false);
        }
        postRepository.delete(optional.get());
        return new ApiResponse("O'chirildi",true);
    }
}
