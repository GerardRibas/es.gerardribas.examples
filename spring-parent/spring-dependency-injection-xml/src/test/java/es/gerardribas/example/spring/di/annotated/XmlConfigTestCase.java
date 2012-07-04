/**
 * 
 */
package es.gerardribas.example.spring.di.annotated;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.gerardribas.example.spring.di.beans.MyBean;

/**
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
 *
 */
public class XmlConfigTestCase {
	
	private ApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void test_xml_config() {
		MyBean bean = ctx.getBean(MyBean.class);
		String helloWorld = bean.getHelloWorld("Gerard");
		Assert.assertEquals("Hello Gerard, this is a PoC Test!", helloWorld);
	}

}
