/**
 * 
 */
package es.gerardribas.example.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.example.common.domain.BillLine;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.example.common.domain.Product;

/**
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring-config.xml")
public class BillDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ProductDao productDao;

	@Test
	public void testFindById() {
		Assert.assertNotNull(billDao.findById(Bill.class, 2L));
	}
	
	@Test
	public void testListAllBills() {
		List<Bill> bills = billDao.findAll(Bill.class);
		Assert.assertNotNull(bills);
	}
	
	@Test
	public void testListFirstTenBills() {
		List<Bill> customers = billDao.findAll(Bill.class,0,10);
		Assert.assertNotNull(customers);
	}
	
	@Test
	public void testPersist() {		
		Bill bill = new Bill();
		
		bill.setCustomer(customerDao.findById(Customer.class, 0L));
		bill.setDate(new Date());
		bill.setPrice(1F);
		
		List<BillLine> list = new ArrayList<BillLine>();
			BillLine billLine = new BillLine();
			billLine.setBill(bill);
			billLine.setPriceLine(1F);
			billLine.setProduct(productDao.findById(Product.class, 0L));
		list.add(billLine);
		
		bill.setDetail(list);
		billDao.persist(bill);
		
		Bill billPersisted = billDao.findById(Bill.class, bill.getId());
		Assert.assertNotNull(billPersisted);
		Assert.assertEquals(bill, billPersisted);
	}

}
