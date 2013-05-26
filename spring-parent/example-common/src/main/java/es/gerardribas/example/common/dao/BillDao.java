package es.gerardribas.example.common.dao;

import java.util.List;

import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.persistence.dao.AbstractDao;

public interface BillDao extends AbstractDao<Bill, Long> {
	
	List<Bill> findBillsByCustomerId(Long customerId);

}
