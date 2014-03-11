package cat.gerardribas.springmvc.rest.api;

import cat.gerardribas.springmvc.rest.api.exception.ResourceNotFoundException;
import es.gerardribas.example.common.domain.Product;
import es.gerardribas.example.common.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gerard on 08/03/2014.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    @RequestMapping
    public
    @ResponseBody
    List<Product> findProducts() {
        return service.findAll();
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    Product findProducts(@PathVariable Long id) {
        Product product = service.findById(id);
        if (product == null) {
            throw new ResourceNotFoundException();
        }
        return product;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Long newProduct(@RequestBody Product entity) {
        service.insert(entity);
        return entity.getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product entity) {
        service.update(entity);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestBody Product entity) {
        service.delete(entity);
    }

    @Resource
    public void setService(ProductService service) {
        this.service = service;
    }

}
