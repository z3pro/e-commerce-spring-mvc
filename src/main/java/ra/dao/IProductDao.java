package ra.dao;

import ra.model.domain.Product;

import java.util.List;

public interface IProductDao extends IGenericDao<Product, Long> {

  List<Product> findByCatalog(Long idCatalog);

  List<Product> findByName(String name);

}
