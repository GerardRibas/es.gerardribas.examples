package es.gerardribas.example.common.dao;

import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.persistence.dao.AbstractDao;

import java.util.List;

public interface BillDao extends AbstractDao<Bill, Long> {

    List<Bill> findBillsByCustomerId(Long customerId);

}
