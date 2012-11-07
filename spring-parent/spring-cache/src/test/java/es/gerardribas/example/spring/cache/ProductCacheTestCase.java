/**
 * 
 */
package es.gerardribas.example.spring.cache;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;

/**
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
 *
 */
@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductCacheTestCase {
	
	@Autowired
	private ProductCacheExample productCacheExample;
	
	@Autowired
	private ProductDao productDao;

	@Test
	public void test() {
		Assert.assertEquals(100, productCacheExample.findProducts().size());
		persistNewProduct();
		Assert.assertEquals(100, productCacheExample.findProducts().size());	
		productCacheExample.loadProductsAgain();
		Assert.assertEquals(101, productCacheExample.findProducts().size());
	}
	
	private void persistNewProduct(){
		Product product = new Product();
		product.setDescription("Cool Water");
		product.setName("Water");
		product.setStock(100);
		product.setUnitPrice(12.3F);
		productDao.persist(product);
	}

}
