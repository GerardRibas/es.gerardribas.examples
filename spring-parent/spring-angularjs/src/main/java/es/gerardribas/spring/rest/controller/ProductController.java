/**
 *
 */
package es.gerardribas.spring.rest.controller;

import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gerard
 */
@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Product> findAll() {
        return productDao.findAll(Product.class);
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Product findProductById(@PathVariable Long productId) {
        Product product = productDao.findById(Product.class, productId);
        return product;
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable Long productId,
                                         @RequestBody Product product) {
        productDao.update(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(
                "The String ResponseBody with custom header Content-Type=application/json",
                headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String msg = "Internal Server Error";
        try {
            Product product = productDao.findById(Product.class, productId);
            productDao.remove(product);
            status = HttpStatus.OK;
            msg = "Successfully Deleted";
        } catch (DataIntegrityViolationException e) {
            msg = "Impossible to delete, this project has bills assoaciated";
            status = HttpStatus.PRECONDITION_FAILED;
        }
        return new ResponseEntity<String>(msg, headers, status);
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productDao.persist(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(
                "The String ResponseBody with custom header Content-Type=application/json",
                headers, HttpStatus.OK);
    }

}
