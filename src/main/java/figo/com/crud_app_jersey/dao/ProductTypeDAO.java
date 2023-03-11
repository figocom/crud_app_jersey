package figo.com.crud_app_jersey.dao;

import figo.com.crud_app_jersey.domain.ProductType;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;

public class ProductTypeDAO extends BaseDAO<ProductType, Integer> {
    private static final ThreadLocal<ProductTypeDAO> productTypeDAOThreadLocal = ThreadLocal.withInitial(ProductTypeDAO::new);

    public static ProductTypeDAO getInstance() {
        return productTypeDAOThreadLocal.get();
    }

    public boolean isPresent(ProductType productType) {
        int byTypeName = findByTypeName(productType.getName_uz());
        return byTypeName > 0;
    }

    private int findByTypeName(String nameUz) {
        begin();
        Query t = em.createNativeQuery("select * from product_type where name_uz=?").setParameter(1, nameUz);
        commit();
        List resultList = t.getResultList();
        return  resultList.size();
    }


    public List<ProductType> findAll() {
        begin();
        Query nativeQuery = em.createNativeQuery("select *from product_type", ProductType.class);
        commit();
        return nativeQuery.getResultList();
    }

    public ProductType findById(Integer id ) {
        begin();
        Query t = em.createNativeQuery("select * from product_type where id=").setParameter(1,id);
        commit();
        return (ProductType) t.getSingleResult();
    }

}
