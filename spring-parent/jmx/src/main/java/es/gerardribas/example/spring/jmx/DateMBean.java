/**
 *
 */
package es.gerardribas.example.spring.jmx;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.Date;

/**
 * @author gerard
 */
@ManagedResource
public interface DateMBean {

    @ManagedOperation
    Date getNewDate();

}
