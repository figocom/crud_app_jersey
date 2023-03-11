package figo.com.crud_app_jersey.domain;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "product_type")
public class ProductType implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer id;
    @Column(nullable = false ,unique = true )
    private String name_uz;
}

