/**
 * 
 */
package es.gerardribas.example.spring.di.annotated;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.gerardribas.example.spring.di.beans.MyBean;

/**
<<<<<<< HEAD
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
=======
 * @author AT
>>>>>>> fef1927956ea183838bcad0df241092c04c3bd30
 *
 */
public class AnnotationConfigTestCase {
	
	private ApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx = new AnnotationConfigApplicationContext(SpringAnnotatedConfiguration.class);
	}
	
	@Test
	public void test_annotated_config() {
		MyBean bean = ctx.getBean(MyBean.class);
		String helloWorld = bean.getHelloWorld("Gerard");
		Assert.assertEquals("Hello Gerard, this is a PoC Test!", helloWorld);
	}

}
