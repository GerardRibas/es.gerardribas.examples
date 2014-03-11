/**
 *
 */
package es.gerardribas.example.spring.cache;

import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
 */
@Component
public class ProductCacheExample {

    private static final Logger logger = LoggerFactory.getLogger(ProductCacheExample.class);

    @Autowired
    private ProductDao productDao;

    @Cacheable(value = "products")
    public List<Product> findProducts() {
        return productDao.findAll(Product.class);
    }

    @CacheEvict(value = "products")
    public void loadProductsAgain() {
        logger.info("Removing Caché...");
    }


}
