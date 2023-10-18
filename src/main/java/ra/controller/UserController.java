package ra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.dto.response.AccountDto;
import ra.service.impl.AccountService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public String user(Model model) {
    List<AccountDto> accountList = accountService.getAll();
    model.addAttribute("accounts", accountList);
    return "/admin/user";
  }
}
