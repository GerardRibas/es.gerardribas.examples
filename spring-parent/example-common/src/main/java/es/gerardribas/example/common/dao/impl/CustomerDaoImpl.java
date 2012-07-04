package es.gerardribas.example.common.dao.impl;

import org.springframework.stereotype.Repository;

import es.gerardribas.example.common.dao.CustomerDao;
import es.gerardribas.example.common.domain.Customer;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;

@SuppressWarnings("serial")
@Repository
public class CustomerDaoImpl extends AbstractJpaDaoImpl<Customer, Long> implements CustomerDao {

}
