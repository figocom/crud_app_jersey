package figo.com.crud_app_jersey.service;

import figo.com.crud_app_jersey.dao.ProductDAO;
import figo.com.crud_app_jersey.dao.ProductTypeDAO;
import figo.com.crud_app_jersey.domain.Product;
import figo.com.crud_app_jersey.domain.ProductType;
import figo.com.crud_app_jersey.dtos.ProductDto;
import figo.com.crud_app_jersey.mapper.ApplicationMapper;

import java.util.List;

public class ApplicationService {
    private static final ThreadLocal<ApplicationService> applicationServiceThreadLocal = ThreadLocal.withInitial(ApplicationService::new);
    public List<Product> getProducts() {
        ProductDAO productDAO = ProductDAO.getInstance();
        return productDAO.getByPage();
    }
    public Product getProduct(Integer id) {
        ProductDAO productDAO = ProductDAO.getInstance();
        return productDAO.findById(id);
    }
    public Product createProduct(ProductDto product) {
        ProductDAO productDAO = ProductDAO.getInstance();
        ApplicationMapper applicationMapper = ApplicationMapper.getInstance();
        Product toProduct = applicationMapper.toProduct(product);
        return productDAO.save(toProduct);
    }
    public Product updateProduct(Product product) {
        ProductDAO productDAO = ProductDAO.getInstance();
        return productDAO.update(product) ? product : null;
    }
    public boolean deleteProduct(Integer id) {
        if (id == null) {
            return false;
        }
        ProductDAO productDAO = ProductDAO.getInstance();
        return productDAO.deleteById(id);
    }
    public Integer getProductsCount() {
        ProductDAO productDAO = ProductDAO.getInstance();
        return productDAO.getCount();
    }
    public List<Product> getProductsByType(Integer typeId) {
        ProductDAO productDAO = ProductDAO.getInstance();
        return productDAO.getByType(typeId);
    }
    public ProductType createProductType(ProductType productType) {
        ProductTypeDAO productTypeDAO = ProductTypeDAO.getInstance();
        if(productTypeDAO.isPresent(productType)) return null;
        return productTypeDAO.save(productType);
    }



    public ProductType updateProductType(ProductType productType) {
        ProductTypeDAO productTypeDAO = ProductTypeDAO.getInstance();
        return productTypeDAO.update(productType) ? productType : null;
    }
    public boolean deleteProductType(Integer id) {
        ProductTypeDAO productTypeDAO = ProductTypeDAO.getInstance();
        return productTypeDAO.deleteById(id);
    }
    public static ApplicationService getInstance() {
        return applicationServiceThreadLocal.get();
    }

    public List<ProductType> getAllProductTypes() {
        return ProductTypeDAO.getInstance().findAll();
    }

    public ProductType getProductType(Integer id) {
        return ProductTypeDAO.getInstance().findById(id);
    }
}
