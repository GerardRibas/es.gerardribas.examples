package es.gerardribas.example.common.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.gerardribas.example.common.dao.BillDao;
import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;

@SuppressWarnings("serial")
@Repository
public class BillDaoImpl extends AbstractJpaDaoImpl<Bill, Long> implements
		BillDao {

	public List<Bill> findBillsByCustomerId(Long customerId) {
		 TypedQuery<Bill> query = getEntityManager().createQuery(
			        "SELECT b FROM Bill b WHERE b.customer.id = :customer",
			        Bill.class);
		query.setParameter("customer", customerId);
		return query.getResultList();
	}

}
