package ra.service;

import org.springframework.web.multipart.MultipartFile;
import ra.model.domain.Product;

import java.util.List;

public interface IProductService extends IGenericService<Product,Long> {
    void save(Product p, MultipartFile file);
    void update(Product p, MultipartFile file);
    List<Product> searchProductByCatalog(Long idCatalog);
    List<Product> findByName(String name);

}
