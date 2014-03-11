/**
 *
 */
package es.gerardribas.example.spring.jmx.impl;

import es.gerardribas.example.spring.jmx.DateMBean;

import java.util.Date;

/**
 * @author gerard
 */
public class DateMBeanImpl implements DateMBean {

    public Date getNewDate() {
        return new Date();
    }

}
