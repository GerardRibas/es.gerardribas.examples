package es.gerardribas.example.common.service;

import es.gerardribas.example.common.domain.Product;

import java.util.List;

/**
 * Created by gerard on 09/03/2014.
 */
public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    void insert(Product product);

    void update(Product product);

    void delete(Product product);
}
