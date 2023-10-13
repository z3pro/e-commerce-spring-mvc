package ra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ra.model.domain.Catalog;
import ra.model.domain.Product;
import ra.model.dto.request.FormLoginDto;
import ra.model.dto.request.FormRegisterDto;
import ra.model.dto.response.AccountDto;
import ra.service.impl.CatalogService;
import ra.service.impl.ProductService;

@Controller
public class HomeController {

  @Autowired
  private ProductService productService;
  @Autowired
  private CatalogService catalogService;


  @GetMapping
  public String home(@ModelAttribute("accountDto") AccountDto accountDto,
      Model model) {
    List<Product> productList = productService.getAll();
    List<Catalog> catalogList = catalogService.getAll();
    if (accountDto.getId() != null) {
      model.addAttribute("accountDto", accountDto);
    }
    model.addAttribute("productList", productList);
    model.addAttribute("catalogs", catalogList);
    model.addAttribute("pro", new Product());
    return "index";
  }

  @GetMapping("/catalog/{id}")
  public String homeCatalog(@ModelAttribute("accountDto") AccountDto accountDto,
      Model model, @PathVariable Long id) {
    List<Product> productList = productService.searchProductByCatalog(id);
    List<Catalog> catalogList = catalogService.getAll();
    if (accountDto.getId() != null) {
      model.addAttribute("accountDto", accountDto);
    }
    model.addAttribute("productList", productList);
    model.addAttribute("catalogs", catalogList);
    model.addAttribute("pro", new Product());
    return "index";
  }

  @GetMapping("/product")
  public String homeSearchProduct(@ModelAttribute("accountDto") AccountDto accountDto,
      Model model, @RequestParam(required = false, value = "name") String name) {
    List<Product> productList = productService.findByName(name);
    List<Catalog> catalogList = catalogService.getAll();
    if (accountDto.getId() != null) {
      model.addAttribute("accountDto", accountDto);
    }
    model.addAttribute("productList", productList);
    model.addAttribute("catalogs", catalogList);
    model.addAttribute("pro", new Product());
    return "index";
  }

  @GetMapping("/user-cart")
  public String cartUser() {
    return "user_cart";
  }

  @GetMapping("/form-login")
  public ModelAndView login() {
    return new ModelAndView("form-login", "form_login", new FormLoginDto());
  }

  @GetMapping("/form-register")
  public String register(Model model) {
    model.addAttribute("form_register", new FormRegisterDto());
    return "form-register";
  }
}
