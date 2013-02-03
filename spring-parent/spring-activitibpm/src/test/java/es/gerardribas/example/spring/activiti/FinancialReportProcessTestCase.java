package es.gerardribas.example.spring.activiti;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class FinancialReportProcessTestCase {

	private static Logger logger = LoggerFactory.getLogger(FinancialReportProcessTestCase.class);

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Test
	public void test() {
		logger.info("Starting process..");
		runtimeService.startProcessInstanceByKey("financialReport");

		/*
		 * Accountancy
		 */

		logger.info("Getting tasks for role accountancy");
		List<Task> list = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		Assert.assertNotNull(list);
		logger.info("Size of tasks for role accountancy: " + list.size());

		for(Task task : list) {
			logger.info("Assign tasks to one user of accountancy group, TaskName=" + task.getName());
			taskService.claim(task.getId(), "fozzie");
		}

		logger.info("Verify assigns of fozzie");
		long countTasks = taskService.createTaskQuery().taskAssignee("fozzie").count();
		Assert.assertEquals(list.size(), countTasks);
		logger.info("Fozzie has " + list.size() + " task(s)");

		for(Task task : list) {
			logger.info("Complete task;" +task.getName()+  " of user fozzie");
			taskService.complete(task.getId());
		}

		countTasks = taskService.createTaskQuery().taskAssignee("fozzie").count();
		Assert.assertEquals(0, countTasks);
		logger.info("All tasks for fozzie are completed, fozzie now don't have work");

		/*
		 * MANAGEMENT
		 */

		logger.info("Getting tasks for role management");
	    list = taskService.createTaskQuery().taskCandidateGroup("management").list();
	    Assert.assertNotNull(list);
		logger.info("Size of tasks for role management: " + list.size());

		for(Task task : list) {
			logger.info("Assign tasks to one user of management group, TaskName=" + task.getName());
			taskService.claim(task.getId(), "kermit");
		}

		logger.info("Verify assigns of kermit");
		countTasks = taskService.createTaskQuery().taskAssignee("kermit").count();
		Assert.assertEquals(list.size(), countTasks);
		logger.info("Kermit has " + list.size() + " task(s)");

		for(Task task : list) {
			logger.info("Complete task;" +task.getName()+  " of user kermit");
			taskService.complete(task.getId());
		}

		countTasks = taskService.createTaskQuery().taskCandidateUser("kermit").count();
		Assert.assertEquals(0, countTasks);
		logger.info("All tasks for kermit are completed, kermit now don't have work");

	}
}