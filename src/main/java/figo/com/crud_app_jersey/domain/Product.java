package figo.com.crud_app_jersey.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Product implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productTypeId;
    @Column(nullable = false , name = "name_uz")
    private String name_uz;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Double cost;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Builder.Default
    private LocalDateTime createdAt= LocalDateTime.now();

}
