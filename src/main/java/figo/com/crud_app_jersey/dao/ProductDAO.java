package figo.com.crud_app_jersey.dao;

import figo.com.crud_app_jersey.domain.Product;

import java.util.List;

public class ProductDAO extends BaseDAO<Product, Integer>{
    private static final ThreadLocal<ProductDAO> productDAOThreadLocal = ThreadLocal.withInitial(ProductDAO::new);

    public static ProductDAO getInstance() {
        return productDAOThreadLocal.get();
    }

    public List<Product> getByPage() {
        begin();
        List<Product> resultList = em.createNativeQuery("select * from product ")
                .getResultList();
        commit();
        return resultList;
    }

    public Integer getCount() {
        begin();
        Integer count = ((Long) em.createQuery("select count(p) from Product p").getSingleResult()).intValue();
        commit();
        return count;
    }

    public List<Product> getByType(Integer typeId) {
        begin();
        List<Product> resultList = em.createQuery("select p from Product p where p.productTypeId = :typeId", Product.class)
                .setParameter("typeId", typeId)
                .getResultList();
        commit();
        return resultList;
    }
}
