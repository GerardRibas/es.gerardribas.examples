package cat.gerardribas.springmvc.rest.api;

import cat.gerardribas.springmvc.rest.config.TestContext;
import cat.gerardribas.springmvc.rest.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.example.common.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by gerard on 09/03/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@WebAppConfiguration
public class CustomerControllerTestCase {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        reset(customerService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindAllProducts() throws Exception {
        Customer first = new Customer();
        first.setName("First Name");
        first.setId(1L);
        first.setCity("First City");
        first.setSurname("First Surname");

        Customer second = new Customer();
        second.setName("Second Name");
        second.setId(1L);
        second.setCity("Second City");
        second.setSurname("Second Surname");

        when(customerService.findAllCustomers()).thenReturn(Arrays.asList(first, second));
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("First Name")))
                .andExpect(jsonPath("$[0].city", Matchers.is("First City")))
                .andExpect(jsonPath("$[0].surname", Matchers.is("First Surname")));
        verify(customerService, times(1)).findAllCustomers();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testFindProductById() throws Exception {
        Customer first = new Customer();
        first.setName("First Name");
        first.setId(1L);
        first.setCity("First City");
        first.setSurname("First Surname");

        when(customerService.findCustomer(1L)).thenReturn(first);
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("First Name")))
                .andExpect(jsonPath("$.city", Matchers.is("First City")))
                .andExpect(jsonPath("$.surname", Matchers.is("First Surname")));
        verify(customerService, times(1)).findCustomer(1L);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testFindProductByIdNotFound() throws Exception {
        when(customerService.findCustomer(1L)).thenReturn(null);
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).findCustomer(1L);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testInsertProduct() throws Exception {
        Customer first = new Customer();
        first.setName("First Name");
        first.setId(1L);
        first.setCity("First City");
        first.setSurname("First Surname");
        mockMvc.perform(post("/customer").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isCreated());
        verify(customerService, times(1)).insert(first);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Customer first = new Customer();
        first.setName("First Name");
        first.setId(1L);
        first.setCity("First City");
        first.setSurname("First Surname");
        mockMvc.perform(put("/customer").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isOk());
        verify(customerService, times(1)).update(first);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Customer first = new Customer();
        first.setName("First Name");
        first.setId(1L);
        first.setCity("First City");
        first.setSurname("First Surname");
        mockMvc.perform(delete("/customer").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isOk());
        verify(customerService, times(1)).delete(first);
        verifyNoMoreInteractions(customerService);
    }


}
