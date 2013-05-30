# Example Activiti BPM

##Descritpion
Proof of concept to show how it works Activiti BPM and Spring Framework.

The example is very simple, I created a JavaDelegate (called SpringServiceTask) that has references to one service (called MyService) that it´s injected by Srping Framework.

##BPM Diagram

You can find the diagram under src/main/resources/process/**financialReport.bpmn20.xml**

![image](http://i44.tinypic.com/34jcgow.png)

It´s very simple, as you can see, it has two user tasks and finally when the last task is done it calls an Injected Spring Service Task.

##Configuration
If you open the **applicationContext.xml** it has the configuration to run Acitivti BPM with Spring Framework. Let´s see here:

	...
	<bean id="processEngineConfiguration" 
		class="org.activiti.spring.SpringProcessEngineConfiguration">
		<property name="dataSource" ref="dataSource" />
		<property name="transactionManager" ref="transactionManager" />
		<property name="databaseSchemaUpdate" value="true" />
		<property name="jobExecutorActivate" value="false" />
		<property name="deploymentResources" value="classpath*:/process/*.bpmn20.xml"/>
	</bean>
	...

**SpringProcessEngineConfiuration** is a factory bean for create the objects that are involved for doing the business process management. Activiti BPM needs a database instance to run it, and I configured with an embedded datasource.

###Create our Service and Add into the BPM Process

We have to declare the **SpringServiceTask** in our **ApplicationContext.xml** and add the reference at the property that want to be wired:

	<bean id="myServiceImpl" class="es.gerardribas.example.spring.activiti.MyServiceImpl" />
	
	<bean id="SpringServiceTask" class="es.gerardribas.example.spring.activiti.SpringServiceTask">
		<property name="service" ref="myServiceImpl" />
	</bean>


##How it works? Let´s try it!

You can test the workflow with a junit test called **FinancialReportProcessTestCase**. This test crates the user task, then assign the task to the one user of the role Accountant that marks the task to complete for following the next step in the workflow and then it creates the task for the maanagement roles, assign to one user, this user complete the task and finally the SpringServiceTask is called with our Service injected. It´s easy to know if it runs because the Service it prints on the console Hello World!. 


##What is in this package
	-src
		main/java
			-es.gerardribas.example.spring.activiti
				-MyService
				-MyServiceImpl
				-SpringServiceTask
		main/resources
			-process
				-financialReport.bpmn20.xml
			-applicationContext.xml
	-test
		-es.gerardribas.example.spring.activiti
			-FinancialReportProcessTestCase


##Who am I?

My name is Gerard Ribas and I am an experienced Software Engineer. You can find more information about me in [![Resize icon][1]](http://ie.linkedin.com/in/gerardribas "Linkedin") or you can follow me in [![Resize icon][2]](https://twitter.com/gerard_ribas "Twitter")

Thank´s for watching!

[1]: http://cdn3.iconfinder.com/data/icons/free-social-icons/67/linkedin_square_color-24.png "Linkedin"

[2]: http://cdn3.iconfinder.com/data/icons/free-social-icons/67/twitter_square-24.png "Twitter"