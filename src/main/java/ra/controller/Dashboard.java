package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.dto.response.AccountDto;

@Controller
@RequestMapping("/admin/dashboard")
public class Dashboard {

  @GetMapping
  public String dashboard(@ModelAttribute("accountDto") AccountDto accountDto, Model model) {
    if (accountDto.getId() != null) {
      model.addAttribute("accountDto", accountDto);
    }
    return "admin/index";
  }
}
