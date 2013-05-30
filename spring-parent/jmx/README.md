# Example JMX

##Descritpion
Proof of concept for showing how to register and publish JMX MBeans with Spring Framework.
The example expose DateMBean that has only one public ManagedOperation that returns the current date. 


##How to publish a MBean with Spring Framework

Annotate your class or interface with **@ManagedResource** and then annotate the methods that you want to expose **@ManagedResource** 

	@ManagedResource
	public interface Date MBean {
	
		@ManagedOperation
		Date getNewDate();
	
	}
	
Also you need to enable the annotations in your application context config file.

##How it works? Let´s try it!

If you want to test the DateMBean Service you can run the DateMBeanTestCase.testClientConnector or load the ApplicationContext from Spring with DateMBeanTestCase.testClientConnectorJConsole and open your jconsole with the following parameters: 

	service:jmx:rmi://localhost/jndi/rmi://localhost:1099/dateService


![image](http://i41.tinypic.com/2gx0hf8.png)

##What is in this package
	-src
		-es.gerardribas.example.spring.jxm
			-DateMBean
		-es.gerardribas.example.spring.jxm.impl
			-DateMBeanImpl

	-test
		-es.gerardribas.example.spring.jxm
			-DateMBeanTestCase


##Who am I?

My name is Gerard Ribas and I am an experienced Software Engineer. You can find more information about me in [![Resize icon][1]](http://ie.linkedin.com/in/gerardribas "Linkedin") or you can follow me in [![Resize icon][2]](https://twitter.com/gerard_ribas "Twitter")

Thank´s for watching!

[1]: http://cdn3.iconfinder.com/data/icons/free-social-icons/67/linkedin_square_color-24.png "Linkedin"

[2]: http://cdn3.iconfinder.com/data/icons/free-social-icons/67/twitter_square-24.png "Twitter"