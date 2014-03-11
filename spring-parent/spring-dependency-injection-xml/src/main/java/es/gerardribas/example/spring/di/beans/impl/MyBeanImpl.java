/**
 *
 */
package es.gerardribas.example.spring.di.beans.impl;

import es.gerardribas.example.spring.di.beans.MyBean;

/**
 * @author Gerard Ribas Canals (gerard.ribas.canals@gmail.com)
 */
public class MyBeanImpl implements MyBean {

    @Override
    public String getHelloWorld(String name) {
        return "Hello " + name + ", this is a PoC Test!";
    }

}
