package ra.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ra.dao.IAccountDao;
import ra.model.domain.Account;
import ra.ultil.ConnectionDB;

@Repository
public class AccountDao implements IAccountDao {

  @Override
  public Account login(String username, String password) {
    Account acc = null;
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = conn.prepareCall(
          "Select * from Accounts where email=? and password=?");
      call.setString(1, username);
      call.setString(2, password);
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        acc = new Account();
        acc.setId(rs.getLong("id"));
        acc.setFullName(rs.getString("full_name"));
        acc.setAddress(rs.getString("address"));
        acc.setPassword(rs.getString("password"));
        acc.setEmail(rs.getString("email"));
        acc.setAvatarUrl(rs.getString("avatar_url"));
        acc.setPhone(rs.getString("phone"));
        acc.setRole(rs.getBoolean("role"));
        acc.setStatus(rs.getBoolean("status"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }

    return acc;
  }

  @Override
  public List<Account> findAll() {
    List<Account> list = new ArrayList<>();
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = conn.prepareCall("Select * from Accounts");
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        Account acc = new Account();
        acc.setId(rs.getLong("id"));
        acc.setFullName(rs.getString("full_name"));
        acc.setAddress(rs.getString("address"));
        acc.setPassword(rs.getString("password"));
        acc.setEmail(rs.getString("email"));
        acc.setAvatarUrl(rs.getString("avatar_url"));
        acc.setPhone(rs.getString("phone"));
        acc.setRole(rs.getBoolean("role"));
        acc.setStatus(rs.getBoolean("status"));
        list.add(acc);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
    return list;
  }

  @Override
  public Account findById(Long id) {
    Account acc = null;
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = conn.prepareCall("Select * from Accounts where id =?");
      call.setLong(1, id);
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        acc = new Account();
        acc.setId(rs.getLong("id"));
        acc.setFullName(rs.getString("full_name"));
        acc.setAddress(rs.getString("address"));
        acc.setPassword(rs.getString("password"));
        acc.setEmail(rs.getString("email"));
        acc.setAvatarUrl(rs.getString("avatar_url"));
        acc.setPhone(rs.getString("phone"));
        acc.setRole(rs.getBoolean("role"));
        acc.setStatus(rs.getBoolean("status"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
    return acc;
  }

  @Override
  public void save(Account account) {
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = null;
      if (account.getId() == null) {
        // thêm mới
        call = conn.prepareCall(
            "Insert Into Accounts(full_name, password, email, phone) values (?,?,?,?)");
        call.setString(1, account.getFullName());
        call.setString(2, account.getPassword());
        call.setString(3, account.getEmail());
        call.setString(4, account.getPhone());
      } else {
        // cật nhật
        call = conn.prepareCall(
            "update  Accounts set full_name=? , phone=?,password=?,address=?,avatar_url=?,status=? where id =?");
        call.setString(1, account.getFullName());
        call.setString(2, account.getPhone());
        call.setString(3, account.getPassword());
        call.setString(4, account.getAddress());
        call.setString(5, account.getAvatarUrl());
        call.setBoolean(6, account.isStatus());
        call.setLong(7, account.getId());
      }

      call.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
  }

  @Override
  public void delete(Long id) {
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = conn.prepareCall("Delete  from Accounts where id =?");
      call.setLong(1, id);
      call.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
  }
}
