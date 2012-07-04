/**
 * 
 */
package es.gerardribas.example.common.dao.impl;

import org.springframework.stereotype.Repository;

import es.gerardribas.example.common.dao.BillLineDao;
import es.gerardribas.example.common.domain.BillLine;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;

/**
 * @author AT
 *
 */
@SuppressWarnings("serial")
@Repository
public class BillLineDaoImpl extends AbstractJpaDaoImpl<BillLine, Long> implements BillLineDao {

}
