package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
