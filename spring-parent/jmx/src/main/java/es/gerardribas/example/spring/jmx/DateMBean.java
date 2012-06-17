/**
 * 
 */
package es.gerardribas.example.spring.jmx;

import java.util.Date;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @author gerard
 *
 */
@ManagedResource
public interface DateMBean {

	@ManagedOperation
	Date getNewDate();
	
}
