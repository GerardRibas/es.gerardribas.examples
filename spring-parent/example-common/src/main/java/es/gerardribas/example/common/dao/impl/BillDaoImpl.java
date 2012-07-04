package es.gerardribas.example.common.dao.impl;

import org.springframework.stereotype.Repository;

import es.gerardribas.example.common.dao.BillDao;
import es.gerardribas.example.common.domain.Bill;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;

@SuppressWarnings("serial")
@Repository
public class BillDaoImpl extends AbstractJpaDaoImpl<Bill, Long> implements BillDao {

}
