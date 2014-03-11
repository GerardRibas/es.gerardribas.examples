package es.gerardribas.example.spring.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class SpringServiceTask implements JavaDelegate {

    private MyService service;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println(service.helloWorld());
    }

    public void setService(MyService service) {
        this.service = service;
    }

}
