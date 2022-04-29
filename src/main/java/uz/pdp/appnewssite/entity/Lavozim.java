package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Lavozim extends AbsEntity {

    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Huquq> huquqList;

    @Column(length = 600)
    private String description;
}
