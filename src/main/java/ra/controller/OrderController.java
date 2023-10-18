package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.domain.Order;
import ra.model.domain.Product;
import ra.model.dto.request.OrderActive;
import ra.model.dto.request.OrderReq;
import ra.model.dto.response.AccountDto;
import ra.service.impl.AccountService;
import ra.service.impl.OrderService;
import ra.service.impl.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private ProductService productService;
  @Autowired
  private OrderService orderService;

  @PostMapping
  public String addOrder(@RequestBody OrderReq orderReq) {
    Product product = productService.findByName(orderReq.getProductName()).get(0);
    System.out.println(product.getId() + "product");
    AccountDto account = accountService.findById(orderReq.getUserId());
    System.out.println(account.getId() + "account");
    orderService.addOrder(account.getId(), orderReq.getFullName(), orderReq.getAddress(),
        orderReq.getPhone(), orderReq.getQuantity() * product.getPrice());
    return "name";

  }

  @PostMapping("/active")
  public ResponseEntity<?> ActiveOrder(@RequestBody OrderActive orderReq) {
    Order order = orderService.findById(orderReq.getOrderId());
    System.out.println(order.getName());
    orderService.activeOrder(order);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
