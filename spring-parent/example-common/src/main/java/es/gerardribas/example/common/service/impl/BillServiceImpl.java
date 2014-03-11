package es.gerardribas.example.common.service.impl;

import es.gerardribas.example.common.dao.BillDao;
import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.example.common.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gerard on 09/03/2014.
 */
@Service
public class BillServiceImpl implements BillService {

    private BillDao billDao;

    @Override
    public List<Bill> findAll() {
        return billDao.findAll(Bill.class);
    }

    @Override
    public Bill findById(Long id) {
        return billDao.findById(Bill.class, id);
    }

    @Override
    public void insert(Bill bill) {
        billDao.persist(bill);
    }

    @Override
    public void update(Bill bill) {
        billDao.update(bill);
    }

    @Override
    public void delete(Bill bill) {
        billDao.remove(bill);
    }

    @Override
    public void delete(Long id) {
        Bill bill = new Bill();
        bill.setId(id);
        delete(bill);
    }

    @Resource
    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

}
