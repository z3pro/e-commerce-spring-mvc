package ra.controller;

import jakarta.validation.Valid;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.dao.impl.AccountDao;
import ra.exception.UserNotFoundException;
import ra.model.domain.Account;
import ra.model.dto.request.FormLoginDto;
import ra.model.dto.request.FormRegisterDto;
import ra.model.dto.response.AccountDto;
import ra.service.IAccountService;
import ra.ultil.ValidateAccount;


@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private ValidateAccount validateAccount;

  @Autowired
  private IAccountService accountService;
  @Autowired
  private AccountDao accountDao;

  @PostMapping("/handle-login")
  public String handleLogin(HttpSession session, RedirectAttributes redirectAttributes,
      @ModelAttribute("form_login") FormLoginDto formLoginDto) throws UserNotFoundException {
    AccountDto accountDto = accountService.login(formLoginDto);
    if (accountDto == null) {
      throw new UserNotFoundException("username or password incorrect!", formLoginDto);
    } else {
      // login success
      redirectAttributes.addFlashAttribute("accountDto", accountDto);
      session.setAttribute("userlogin", accountDto);
      if (accountDto.isStatus()) {
        if (accountDto.isRole()) {
          return "redirect:/admin/dashboard";
        } else {
          return "redirect:/";
        }
      } else {
        throw new UserNotFoundException("account is locked!", formLoginDto);
      }
    }
  }

  @PostMapping("/handle-register")
  public String handleRegister(
      @ModelAttribute("form_register") @Valid FormRegisterDto formRegisterDto,
      BindingResult bindingResult, Model model) {
    // kiem tra email co ton tai chua
    validateAccount.checkAccount(formRegisterDto, bindingResult);

    if (bindingResult.hasErrors()) {
      model.addAttribute("form_register", formRegisterDto);
      return "form-register";
    }
    accountService.register(formRegisterDto);
    return "redirect:/form-login";
  }

  @PostMapping("/activeOrBlock")
  public ResponseEntity<?> activeOrBlock(@RequestBody Long id) {
    System.out.println(id);
    Account accountDto = accountDao.findById(id);
    if (accountDto.isStatus()) {
      accountDto.setStatus(false);
    } else {
      accountDto.setStatus(true);
    }
    accountDao.save(accountDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
