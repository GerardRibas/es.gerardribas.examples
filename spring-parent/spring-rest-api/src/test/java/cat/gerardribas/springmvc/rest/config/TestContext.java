package cat.gerardribas.springmvc.rest.config;

import es.gerardribas.example.common.service.BillService;
import es.gerardribas.example.common.service.CustomerService;
import es.gerardribas.example.common.service.ProductService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gerard on 09/03/2014.
 */
@Configuration
public class TestContext {

    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }

    @Bean
    public BillService billService() {
        return Mockito.mock(BillService.class);
    }

    @Bean
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }


}
