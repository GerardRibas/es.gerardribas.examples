package es.gerardribas.example.common.service.impl;

import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;
import es.gerardribas.example.common.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gerard on 09/03/2014.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll(Product.class);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(Product.class, id);
    }

    @Override
    public void insert(Product product) {
        productDao.persist(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Product product) {
        productDao.remove(product);
    }

    @Resource
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
