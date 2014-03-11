package cat.gerardribas.springmvc.rest.api;

import cat.gerardribas.springmvc.rest.config.TestContext;
import cat.gerardribas.springmvc.rest.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.gerardribas.example.common.domain.Product;
import es.gerardribas.example.common.service.ProductService;
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
public class ProductControllerTestCase {

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
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        reset(productService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindAllProducts() throws Exception {
        Product first = new Product();
        first.setId(1L);
        first.setDescription("First Description");
        first.setName("First Name");
        first.setStock(1);
        first.setUnitPrice(99F);

        Product second = new Product();
        second.setId(2L);
        second.setDescription("Second Description");
        second.setName("Second Name");
        second.setStock(2);
        second.setUnitPrice(77F);
        when(productService.findAll()).thenReturn(Arrays.asList(first, second));
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].description", Matchers.is("First Description")))
                .andExpect(jsonPath("$[0].name", Matchers.is("First Name")))
                .andExpect(jsonPath("$[0].stock", Matchers.is(1)))
                .andExpect(jsonPath("$[0].unitPrice", Matchers.is(99.0)));
        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testFindProductById() throws Exception {
        Product first = new Product();
        first.setId(1L);
        first.setDescription("First Description");
        first.setName("First Name");
        first.setStock(1);
        first.setUnitPrice(99F);

        when(productService.findById(1L)).thenReturn(first);
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.description", Matchers.is("First Description")))
                .andExpect(jsonPath("$.name", Matchers.is("First Name")))
                .andExpect(jsonPath("$.stock", Matchers.is(1)))
                .andExpect(jsonPath("$.unitPrice", Matchers.is(99.0)));
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testFindProductByIdNotFound() throws Exception {
        when(productService.findById(1L)).thenReturn(null);
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isNotFound());
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testInsertProduct() throws Exception {
        Product first = new Product();
        first.setId(1L);
        first.setDescription("First Description");
        first.setName("First Name");
        first.setStock(1);
        first.setUnitPrice(99F);
        mockMvc.perform(post("/product").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isCreated());
        verify(productService, times(1)).insert(first);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product first = new Product();
        first.setId(1L);
        first.setDescription("First Description");
        first.setName("First Name");
        first.setStock(1);
        first.setUnitPrice(99F);
        mockMvc.perform(put("/product").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isOk());
        verify(productService, times(1)).update(first);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product first = new Product();
        first.setId(1L);
        first.setDescription("First Description");
        first.setName("First Name");
        first.setStock(1);
        first.setUnitPrice(99F);
        mockMvc.perform(delete("/product").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(first)))
                .andExpect(status().isOk());
        verify(productService, times(1)).delete(first);
        verifyNoMoreInteractions(productService);
    }


}
