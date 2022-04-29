package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appnewssite.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends AbsEntity {
    @Column(nullable = false,columnDefinition = "text")
    private String title;

    @Column(nullable = false,columnDefinition = "text")
    private String text;

    @Column(nullable = false,columnDefinition = "text")
    private String url;
}
