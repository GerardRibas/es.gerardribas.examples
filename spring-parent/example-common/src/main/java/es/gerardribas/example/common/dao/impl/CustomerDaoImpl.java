package es.gerardribas.example.common.dao.impl;

import es.gerardribas.example.common.dao.CustomerDao;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;
import org.springframework.stereotype.Repository;

@SuppressWarnings("serial")
@Repository
public class CustomerDaoImpl extends AbstractJpaDaoImpl<Customer, Long> implements CustomerDao {

}
