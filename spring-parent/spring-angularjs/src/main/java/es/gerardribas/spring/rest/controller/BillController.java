/**
 *
 */
package es.gerardribas.spring.rest.controller;

import es.gerardribas.example.common.dao.BillDao;
import es.gerardribas.example.common.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gerard
 */
@Controller
public class BillController {

    @Autowired
    private BillDao billDao;

    @RequestMapping(value = "/bill/{billId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Bill> findBillsByCustomerId(@PathVariable Long billId) {
        List<Bill> bills = billDao.findBillsByCustomerId(billId);
        return bills;
    }

}
