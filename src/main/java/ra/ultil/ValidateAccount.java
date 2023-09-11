package ra.ultil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ra.model.dto.request.FormRegisterDto;
import ra.service.IAccountService;
@Component
public class ValidateAccount {
    @Autowired
    private IAccountService accountService;
    public  void checkAccount(FormRegisterDto formRegisterDto, Errors error){
        if (accountService.existByEmail(formRegisterDto.getEmail())){
            error.rejectValue("email","message.err.email_exist");
        }
        if (formRegisterDto.getFullName().trim().length()<6){
            error.rejectValue("fullName","message.err.full_name_invalid");
        }
        if (!formRegisterDto.getEmail().matches("^(.+)@(\\S+)$")){
            error.rejectValue("email","message.err.email_invalid");
        }
        if (!formRegisterDto.getPhone().matches("^0[0-9]{9,10}$")){
            error.rejectValue("phone","message.err.phone_invalid");
        }
        if (!formRegisterDto.getRePassword().equals(formRegisterDto.getPassword())){
            error.rejectValue("rePassword","message.err.password_confirm");
        }

    }
}
