package es.gerardribas.example.common.dao.impl;

import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;
import org.springframework.stereotype.Repository;

@SuppressWarnings("serial")
@Repository
public class ProductDaoImpl extends AbstractJpaDaoImpl<Product, Long> implements ProductDao {

}
