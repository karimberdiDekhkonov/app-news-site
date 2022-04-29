package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @NotNull(message = "username bo'sh bo'lmasin")
    private String text;

    @NotNull
    private Long postId;
}
