package ra.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Service;
import ra.model.domain.Order;
import ra.ultil.ConnectionDB;

@Service
public class OrderDao {

  public List<Order> findAll() {
    List<Order> list = new ArrayList<>();
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = conn.prepareCall(("select * from orders"));
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("userId"));
        order.setName(rs.getString("name"));
        order.setPhone(rs.getString("phone"));
        order.setAddress(rs.getString("address"));
        order.setStatus(rs.getBoolean("status"));
        order.setTotal(rs.getDouble("total"));
        order.setOrderAt(rs.getDate("orderAt"));
        list.add(order);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
    return list;
  }

  public List<Order> findByUserId(Long userId) {
    List<Order> list = null;
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = null;
      call = conn.prepareCall("select * from orders where userId =?");
      call.setLong(1, userId);
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("userId"));
        order.setName(rs.getString("name"));
        order.setPhone(rs.getString("phone"));
        order.setAddress(rs.getString("address"));
        order.setStatus(rs.getBoolean("status"));
        order.setTotal(rs.getDouble("total"));
        order.setOrderAt(rs.getDate("orderAt"));
        list.add(order);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
    return list;
  }

  public Order findById(Long id) {
    Order order = null;
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = null;
      call = conn.prepareCall(("select * from orders where id = ?"));
      call.setLong(1, id);
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        order = new Order();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("userId"));
        order.setName(rs.getString("name"));
        order.setPhone(rs.getString("phone"));
        order.setAddress(rs.getString("address"));
        order.setStatus(rs.getBoolean("status"));
        order.setTotal(rs.getDouble("total"));
        order.setOrderAt(rs.getDate("orderAt"));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
    return order;
  }

  public void addOrder(Long userId, String name, String address, String phone, double total) {
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    Connection conn = ConnectionDB.getConnection();
    try {
      CallableStatement call = null;
      call = conn.prepareCall(
          "Insert Into orders(userId,name,phone,address,status,total,orderAt) values (?,?,?,?,?,?,?)");
      call.setLong(1, userId);
      call.setString(2, name);
      call.setString(3, phone);
      call.setString(4, address);
      call.setBoolean(5, false);
      call.setDouble(6, total);
      call.setDate(7, date);
      call.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
  }

  public void activeOrder(Order order) {
    Connection conn = ConnectionDB.getConnection();
    System.out.println(order.getOrderAt());
    try {
      CallableStatement call = null;
      call = conn.prepareCall(
          "update orders set userId=?, name=?, phone=?, address=?, status=?, total=?, orderAt=? where id=?");
      call.setLong(1, order.getUserId());
      call.setString(2, order.getName());
      call.setString(3, order.getPhone());
      call.setString(4, order.getAddress());
      call.setBoolean(5, true);
      call.setDouble(6, order.getTotal());
      call.setDate(7, new java.sql.Date(order.getOrderAt().getTime()));
      call.setLong(8, order.getId());
      call.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      ConnectionDB.close(conn);
    }
  }
}

