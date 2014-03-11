package cat.gerardribas.springmvc.rest.api;

import cat.gerardribas.springmvc.rest.api.exception.ResourceNotFoundException;
import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.example.common.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gerard on 08/03/2014.
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    private BillService service;

    @RequestMapping
    public
    @ResponseBody
    List<Bill> findProducts() {
        return service.findAll();
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    Bill findBillById(@PathVariable Long id) {
        Bill bill = service.findById(id);
        if (bill == null) {
            throw new ResourceNotFoundException();
        }
        return bill;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Long newBill(@RequestBody Bill entity) {
        service.insert(entity);
        return entity.getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateBill(@RequestBody Bill entity) {
        service.update(entity);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBill(@RequestBody Bill entity) {
        service.delete(entity);
    }

    @Resource
    public void setService(BillService service) {
        this.service = service;
    }

}
