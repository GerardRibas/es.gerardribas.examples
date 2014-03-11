/**
 *
 */
package es.gerardribas.example.common.dao.impl;

import es.gerardribas.example.common.dao.BillLineDao;
import es.gerardribas.example.common.domain.BillLine;
import es.gerardribas.persistence.dao.impl.AbstractJpaDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author
 */
@SuppressWarnings("serial")
@Repository
public class BillLineDaoImpl extends AbstractJpaDaoImpl<BillLine, Long> implements BillLineDao {

}
