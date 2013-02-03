/**
 * 
 */
package es.gerardribas.example.common.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.gerardribas.example.common.domain.Product;

/**
 * @author Gerard
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring-config.xml")
public class ProductDaoTestCase {

	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testFindById() {
		Assert.assertNotNull(productDao.findById(Product.class, 2L));
	}
	
	@Test
	public void testListAllProducts() {
		List<Product> products = productDao.findAll(Product.class);
		Assert.assertNotNull(products);
		Assert.assertEquals(100, products.size());
	}
	
	@Test
	public void testListFirstTenProducts() {
		List<Product> products = productDao.findAll(Product.class,0,10);
		Assert.assertNotNull(products);
		Assert.assertEquals(10, products.size());
	}
	
	@Test(expected=javax.persistence.PersistenceException.class)
	public void testPersistWithANullValue() {
		Product product = new Product();
		product.setName("A");
		product.setDescription("B");
		product.setStock(2);
		productDao.persist(product);
	}
	
	public void testPersist() {
		Product product = new Product();
		product.setName("A");
		product.setDescription("B");
		product.setStock(2);
		product.setUnitPrice(2.2F);
		productDao.persist(product);
		Product productoFromDB = productDao.findById(Product.class, 100L);
		Assert.assertNotNull(productoFromDB);
		Assert.assertEquals(product, productoFromDB);
	}
	
}
