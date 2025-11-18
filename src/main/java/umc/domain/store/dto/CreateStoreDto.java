package umc.domain.store.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.store.enums.Category;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStoreDto {


    @NotEmpty
    private String name;


    @NotBlank
    private String address;


    @NotBlank
    private Category category;


    @Min(value = 1)
    @Max(value = 5)
    private Integer rate;
}
