package ra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.domain.Order;
import ra.service.impl.OrderService;

@Controller
@RequestMapping("/admin/active-order")
public class OrderAdminController {

  @Autowired
  private OrderService orderService;

  @GetMapping
  public String getOrderAdmin(Model model) {
    System.out.println(model);
    List<Order> orders = orderService.findAll();
    System.out.println(orders);
    model.addAttribute("orders", orders);
    return "admin/active-order";

  }
}
