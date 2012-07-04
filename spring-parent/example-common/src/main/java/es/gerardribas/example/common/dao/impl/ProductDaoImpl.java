package es.gerardribas.example.common.dao.impl;

import org.springframework.stereotype.Repository;

import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;

@SuppressWarnings("serial")
@Repository
public class ProductDaoImpl extends AbstractJpaDaoImpl<Product, Long> implements ProductDao {

}
