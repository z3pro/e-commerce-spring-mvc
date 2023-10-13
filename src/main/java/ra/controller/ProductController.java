package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.model.domain.Catalog;
import ra.model.domain.Product;
import ra.service.ICatalogService;
import ra.service.IProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

  private static final Gson GSON = new GsonBuilder().create();
  @Autowired
  private ICatalogService catalogService;
  @Autowired
  private IProductService productService;

  @GetMapping
  public String product(Model model, @RequestParam(required = false, value = "name") String name) {
    List<Product> listPro =
        name != null ? productService.findByName(name) : productService.getAll();
    List<Catalog> listCat = catalogService.getAll();
    model.addAttribute("listPro", listPro);
    model.addAttribute("listCat", listCat);
    model.addAttribute("pro", new Product());
    return "admin/product";
  }

  @PostMapping("/add")
  public String doAdd(@ModelAttribute("pro") Product pro,
      @RequestParam("file") MultipartFile file) {
    productService.save(pro, file);
    return "redirect:/admin/product";
  }

  @GetMapping(value = "/edit/{id}")
  public void edit(HttpServletResponse response, @PathVariable("id") Long id) {
    Product pro = productService.findById(id);
    String data = GSON.toJson(pro);
    response.setHeader("Content-Type", "application/json");
    response.setStatus(200);
    PrintWriter out = null;
    try {
      out = response.getWriter();
      out.write(data);
      out.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      out.close();
    }
  }

  @PostMapping("/update")
  public String doUpdate(@ModelAttribute Product product, @RequestParam MultipartFile file) {
    productService.update(product, file);
    return "redirect:/admin/product";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    productService.deleteById(id);
    return "redirect:/admin/product";
  }

  @GetMapping("/catalogId/{id}")
  public String getByCatalog(@PathVariable("id") Long id, Model model) {
    List<Product> products = productService.searchProductByCatalog(id);
    List<Catalog> listCat = catalogService.getAll();
    model.addAttribute("listPro", products);
    model.addAttribute("listCat", listCat);
    model.addAttribute("pro", new Product());
    return "admin/product";
  }
}
