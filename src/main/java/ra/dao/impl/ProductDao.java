package ra.dao.impl;

import org.springframework.stereotype.Repository;
import ra.dao.IProductDao;
import ra.model.domain.Catalog;
import ra.model.domain.Product;
import ra.ultil.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao implements IProductDao {
    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("Select * from product");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Product pro =new Product();
                pro.setId(rs.getLong("id"));
                pro.setName(rs.getString("name"));
                pro.setDescription(rs.getString("description"));
                pro.setPrice(rs.getDouble("price"));
                pro.setImageUrl(rs.getString("image_url"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
                pro.setCatalogId(rs.getInt("catalog_id"));
                list.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
        return list;
    }

    @Override
    public Product findById(Long id) {
        Product pro = null;
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("Select * from product where  id =?");
            call.setLong(1,id);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                pro =new Product();
                pro.setId(rs.getLong("id"));
                pro.setName(rs.getString("name"));
                pro.setDescription(rs.getString("description"));
                pro.setPrice(rs.getDouble("price"));
                pro.setImageUrl(rs.getString("image_url"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
                pro.setCatalogId(rs.getInt("catalog_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
        return  pro;
    }

    @Override
    public void save(Product product) {
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call =null;
            if(product.getId()==null){
                // thêm mới
                call = conn.prepareCall("Insert Into product(name,description,price,image_url,stock,status,catalog_id) values (?,?,?,?,?,?,?)");
                call.setString(1,product.getName());
                call.setString(2,product.getDescription());
                call.setDouble(3,product.getPrice());
                call.setString(4,product.getImageUrl());
                call.setInt(5,product.getStock());
                call.setBoolean(6,product.isStatus());
                call.setInt(7,product.getCatalogId());
            }else {
                // cật nhật
                call = conn.prepareCall("update product set name=?, description=? , price= ?,image_url=?, stock=?, status=?, catalog_id=?  where id =?");
                call.setString(1,product.getName());
                call.setString(2,product.getDescription());
                call.setDouble(3,product.getPrice());
                call.setString(4,product.getImageUrl());
                call.setInt(5,product.getStock());
                call.setBoolean(6,product.isStatus());
                call.setInt(7,product.getCatalogId());
                call.setLong(8,product.getId());
            }

            call.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
    }

    @Override
    public void delete(Long id) {
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("Delete  from product where id =?");
            call.setLong(1,id);
            call.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
    }
}
