package es.gerardribas.example.common.service;

import es.gerardribas.example.common.domain.Bill;

import java.util.List;

/**
 * Created by gerard on 09/03/2014.
 */
public interface BillService {
    List<Bill> findAll();

    Bill findById(Long id);

    void insert(Bill bill);

    void update(Bill bill);

    void delete(Bill bill);

    void delete(Long id);
}
