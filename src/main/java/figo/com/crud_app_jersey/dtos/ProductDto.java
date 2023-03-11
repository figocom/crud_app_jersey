package figo.com.crud_app_jersey.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private String name_uz;
    private String cost;
    private String address;
    private String type_id;
}
