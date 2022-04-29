package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull(message = "fullName bo'sh bo'lmasin")
    private String fullName;

    @NotNull(message = "username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "parol bo'sh bo'lmasin")
    private String password;

    @NotNull(message = "Takroriy parol bo'sh bo'lmasin")
    private String prePassword;
}
