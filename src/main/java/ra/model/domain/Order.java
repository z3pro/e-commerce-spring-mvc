package ra.model.domain;


import java.sql.Date;

public class Order {

  private Long id;
  private Long userId;
  private String name;
  private String phone;
  private String address;
  private boolean status;
  private double total;
  private Date orderAt;

  public Order() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Date getOrderAt() {
    return orderAt;
  }

  public void setOrderAt(Date orderAt) {
    this.orderAt = orderAt;
  }
}
