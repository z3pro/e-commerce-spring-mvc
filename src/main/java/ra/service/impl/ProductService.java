package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ra.dao.IProductDao;
import ra.model.domain.Product;
import ra.service.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class ProductService implements IProductService {
    private String uploadPath = "C:\\Users\\hung1\\OneDrive\\Desktop\\ProjectMd4\\src\\main\\webapp\\WEB-INF\\upload\\";
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void save(Product p, MultipartFile file) {
        // xử lí upload trước
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(uploadPath+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        p.setImageUrl(fileName);
        productDao.save(p);
    }

    @Override
    public void update(Product p, MultipartFile file) {
        String fileName = null;
        if(file.isEmpty()){
            fileName=productDao.findById(p.getId()).getImageUrl();
        }else {
            fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(),new File(uploadPath+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        p.setImageUrl(fileName);
        productDao.save(p);
    }

    @Override
    public void deleteById(Long aLong) {
        productDao.delete(aLong);
    }
}
