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
import ra.model.domain.Catalog;
import ra.service.ICatalogService;

@Controller
@RequestMapping("/admin/catalog")
public class CatalogController {

  private static final Gson GSON = new GsonBuilder().create();
  @Autowired
  private ICatalogService catalogService;

  @GetMapping
  public String catalog(Model model) {
    List<Catalog> list = catalogService.getAll();
    model.addAttribute("list", list);
    model.addAttribute("cat", new Catalog());
    return "admin/category";
  }

  @PostMapping("/add")
  public String doAdd(@ModelAttribute("cat") Catalog cat) {
    catalogService.save(cat);
    return "redirect:/admin/catalog";
  }

  @GetMapping(value = "/edit/{id}")
  public void edit(HttpServletResponse response, @PathVariable("id") Long id) {
    Catalog cat = catalogService.findById(id);
    String data = GSON.toJson(cat);
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
  public String doUpdate(@RequestParam Long id, @RequestParam String name,
      @RequestParam String description) {
    Catalog catalog = new Catalog(id, name, description);
    catalogService.save(catalog);
    return "redirect:/admin/catalog";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    catalogService.deleteById(id);
    return "redirect:/admin/catalog";
  }

}
