package ra.dao.impl;

import org.springframework.stereotype.Repository;
import ra.dao.ICatalogDao;
import ra.model.domain.Account;
import ra.model.domain.Catalog;
import ra.ultil.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CatalogDao implements ICatalogDao {
    @Override
    public List<Catalog> findAll() {
        List<Catalog> list = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("Select * from Catalog");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Catalog cat =new Catalog();
                cat.setId(rs.getLong("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
                list.add(cat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
        return list;
    }

    @Override
    public Catalog findById(Long id) {
      Catalog cat = null;
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("Select * from Catalog where  id =?");
            call.setLong(1,id);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                cat = new Catalog();
                cat.setId(rs.getLong("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
        return cat;
    }

    @Override
    public void save(Catalog catalog) {
        Connection conn = ConnectionDB.getConnection();
        try {
            CallableStatement call =null;
            if(catalog.getId()==null){
                // thêm mới
                call = conn.prepareCall("Insert Into catalog(name,description) values (?,?)");
                call.setString(1,catalog.getName());
                call.setString(2,catalog.getDescription());
            }else {
                // cật nhật
                call = conn.prepareCall("update catalog set name=?, description=? where id =?");
                call.setString(1,catalog.getName());
                call.setString(2,catalog.getDescription());
                call.setLong(3,catalog.getId());
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
            CallableStatement call = conn.prepareCall("Delete  from catalog where id =?");
            call.setLong(1,id);
            call.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.close(conn);
        }
    }
}
