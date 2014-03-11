package cat.gerardribas.springmvc.rest.api;

import cat.gerardribas.springmvc.rest.config.TestContext;
import cat.gerardribas.springmvc.rest.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.example.common.domain.BillLine;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.example.common.domain.Product;
import es.gerardribas.example.common.service.BillService;
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
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by gerard on 09/03/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@WebAppConfiguration
public class BillControllerTestCase {

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
    private BillService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        reset(productService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("First Description");
        product1.setName("First Name");
        product1.setStock(1);
        product1.setUnitPrice(99F);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setDescription("Second Description");
        product2.setName("Second Name");
        product2.setStock(2);
        product2.setUnitPrice(77F);

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer Name for first bill");
        customer1.setSurname("Customer Surname for first bill");
        customer1.setCity("Customer City for first bill");

        BillLine line = new BillLine();
        Bill first = new Bill();
        first.setId(1L);
        first.setCustomer(customer1);
        Date billDate = new Date();
        first.setDate(billDate);
        first.setPrice(1F);
        first.setDetail(Arrays.asList(line));
        line.setId(1L);
        line.setBill(first);
        line.setPriceLine(1.33F);
        line.setProduct(product1);

        BillLine line2 = new BillLine();
        Bill second = new Bill();
        second.setId(2L);
        second.setCustomer(customer1);
        second.setDate(new Date());
        second.setPrice(1F);
        second.setDetail(Arrays.asList(line));
        line2.setId(2L);
        line2.setBill(second);
        line2.setPriceLine(1.33F);
        line2.setProduct(product2);

        when(productService.findAll()).thenReturn(Arrays.asList(first, second));
        mockMvc.perform(get("/bill"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].customer.id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].customer.name", Matchers.is("Customer Name for first bill")))
                .andExpect(jsonPath("$[0].customer.surname", Matchers.is("Customer Surname for first bill")))
                .andExpect(jsonPath("$[0].customer.city", Matchers.is("Customer City for first bill")))
                .andExpect(jsonPath("$[0].date", Matchers.is(billDate.getTime())))
                .andExpect(jsonPath("$[0].price", Matchers.is(1.0)))
                .andExpect(jsonPath("$[0].detail", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].detail[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].detail[0].priceLine", Matchers.is(1.33)))
                .andExpect(jsonPath("$[0].detail[0].product.id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].detail[0].product.description", Matchers.is("First Description")))
                .andExpect(jsonPath("$[0].detail[0].product.name", Matchers.is("First Name")))
                .andExpect(jsonPath("$[0].detail[0].product.stock", Matchers.is(1)))
                .andExpect(jsonPath("$[0].detail[0].product.unitPrice", Matchers.is(99.0)));
        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testFindById() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("First Description");
        product1.setName("First Name");
        product1.setStock(1);
        product1.setUnitPrice(99F);

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer Name for first bill");
        customer1.setSurname("Customer Surname for first bill");
        customer1.setCity("Customer City for first bill");

        BillLine line = new BillLine();
        Bill first = new Bill();
        first.setId(1L);
        first.setCustomer(customer1);
        Date billDate = new Date();
        first.setDate(billDate);
        first.setPrice(1F);
        first.setDetail(Arrays.asList(line));
        line.setId(1L);
        line.setBill(first);
        line.setPriceLine(1.33F);
        line.setProduct(product1);

        when(productService.findById(1L)).thenReturn(first);
        mockMvc.perform(get("/bill/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.customer.id", Matchers.is(1)))
                .andExpect(jsonPath("$.customer.name", Matchers.is("Customer Name for first bill")))
                .andExpect(jsonPath("$.customer.surname", Matchers.is("Customer Surname for first bill")))
                .andExpect(jsonPath("$.customer.city", Matchers.is("Customer City for first bill")))
                .andExpect(jsonPath("$.date", Matchers.is(billDate.getTime())))
                .andExpect(jsonPath("$.price", Matchers.is(1.0)))
                .andExpect(jsonPath("$.detail", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.detail[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.detail[0].priceLine", Matchers.is(1.33)))
                .andExpect(jsonPath("$.detail[0].product.id", Matchers.is(1)))
                .andExpect(jsonPath("$.detail[0].product.description", Matchers.is("First Description")))
                .andExpect(jsonPath("$.detail[0].product.name", Matchers.is("First Name")))
                .andExpect(jsonPath("$.detail[0].product.stock", Matchers.is(1)))
                .andExpect(jsonPath("$.detail[0].product.unitPrice", Matchers.is(99.0)));
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        when(productService.findById(1L)).thenReturn(null);
        mockMvc.perform(get("/bill/1"))
                .andExpect(status().isNotFound());
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testPost() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("First Description");
        product1.setName("First Name");
        product1.setStock(1);
        product1.setUnitPrice(99F);
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer Name for first bill");
        customer1.setSurname("Customer Surname for first bill");
        customer1.setCity("Customer City for first bill");

        BillLine line = new BillLine();
        Bill first = new Bill();
        first.setId(1L);
        first.setCustomer(customer1);
        Date billDate = new Date();
        first.setDate(billDate);
        first.setPrice(1F);
        first.setDetail(Arrays.asList(line));
        line.setId(1L);
        line.setBill(first);
        line.setPriceLine(1.33F);
        line.setProduct(product1);

        mockMvc.perform(post("/bill").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isCreated());
        verify(productService, times(1)).insert(first);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testPut() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("First Description");
        product1.setName("First Name");
        product1.setStock(1);
        product1.setUnitPrice(99F);
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer Name for first bill");
        customer1.setSurname("Customer Surname for first bill");
        customer1.setCity("Customer City for first bill");

        BillLine line = new BillLine();
        Bill first = new Bill();
        first.setId(1L);
        first.setCustomer(customer1);
        Date billDate = new Date();
        first.setDate(billDate);
        first.setPrice(1F);
        first.setDetail(Arrays.asList(line));
        line.setId(1L);
        line.setBill(first);
        line.setPriceLine(1.33F);
        line.setProduct(product1);

        mockMvc.perform(put("/bill").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isOk());
        verify(productService, times(1)).update(first);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testDelete() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setDescription("First Description");
        product1.setName("First Name");
        product1.setStock(1);
        product1.setUnitPrice(99F);
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer Name for first bill");
        customer1.setSurname("Customer Surname for first bill");
        customer1.setCity("Customer City for first bill");

        BillLine line = new BillLine();
        Bill first = new Bill();
        first.setId(1L);
        first.setCustomer(customer1);
        Date billDate = new Date();
        first.setDate(billDate);
        first.setPrice(1F);
        first.setDetail(Arrays.asList(line));
        line.setId(1L);
        line.setBill(first);
        line.setPriceLine(1.33F);
        line.setProduct(product1);

        mockMvc.perform(delete("/bill").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isOk());
        verify(productService, times(1)).delete(first);
        verifyNoMoreInteractions(productService);
    }
}
