package figo.com.crud_app_jersey.mapper;

import figo.com.crud_app_jersey.domain.Product;
import figo.com.crud_app_jersey.dtos.ProductDto;

public class ApplicationMapper {
    private static final ThreadLocal<ApplicationMapper> applicationMapperThreadLocal = ThreadLocal.withInitial(ApplicationMapper::new);
    public static ApplicationMapper getInstance() {
        return applicationMapperThreadLocal.get();
    }
    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .type_id(String.valueOf(product.getProductTypeId()))
                .name_uz(product.getName_uz())
                .address(product.getAddress())
                .cost(String.valueOf(product.getCost()))
                .build();
    }
    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .productTypeId(Integer.valueOf(productDto.getType_id()))
                .name_uz(productDto.getName_uz())
                .address(productDto.getAddress())
                .cost(Double.valueOf(productDto.getCost()))
                .build();
    }
}
