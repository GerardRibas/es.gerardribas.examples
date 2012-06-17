/**
 * 
 */
package es.gerardribas.example.spring.jmx.impl;

import java.util.Date;

import es.gerardribas.example.spring.jmx.DateMBean;

/**
 * @author gerard
 *
 */
public class DateMBeanImpl implements DateMBean {

	public Date getNewDate() {
		return new Date();
	}

}
