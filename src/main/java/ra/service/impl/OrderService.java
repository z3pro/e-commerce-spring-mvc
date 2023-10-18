package ra.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.impl.OrderDao;
import ra.model.domain.Order;

@Service
public class OrderService {

  @Autowired
  private OrderDao orderDao;

  public List<Order> findAll() {
    return orderDao.findAll();
  }

  public Order findById(Long id) {
    return orderDao.findById(id);
  }

  public void addOrder(Long userId, String name, String address, String phone, double total) {
    orderDao.addOrder(userId, name, address, phone, total);
  }

  public void activeOrder(Order order) {
    orderDao.activeOrder(order);
  }
}
