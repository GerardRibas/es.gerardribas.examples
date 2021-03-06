/**
 *
 */
package es.gerardribas.example.spring.jmx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author gerard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class DateMBeanTestCase {

    @Autowired
    @Qualifier(value = "clientConnector")
    private MBeanServerConnection mBeanServerConnection;

    @Test
    public void testClientConnector() throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, IOException, IntrospectionException, ReflectionException, MBeanException {
        Assert.assertNotNull(mBeanServerConnection);
        ObjectName objectName = new ObjectName("bean:name=dateService");
        MBeanInfo mBeanInfo = mBeanServerConnection.getMBeanInfo(objectName);
        Assert.assertNotNull(mBeanInfo);
        String operationName = "getNewDate";
        Date dateJMX = (Date) mBeanServerConnection.invoke(objectName, operationName, null, null);
        Assert.assertNotNull(dateJMX);
    }

    @Test
    public void testClientConnectorWithJConsole() throws InterruptedException {
        System.out.println("Time to open your jconsole with these parameters:");
        Thread.sleep(5000L);
    }
}
