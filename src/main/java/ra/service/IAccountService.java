package ra.service;

import ra.model.dto.request.FormLoginDto;
import ra.model.dto.request.FormRegisterDto;
import ra.model.dto.request.FormUpdateAccount;
import ra.model.dto.response.AccountDto;

import java.util.List;

public interface IAccountService{
    List<AccountDto> getAll();
    AccountDto findById(Long id);
    void register(FormRegisterDto formRegisterDto);
    void updateAccountInfo(FormUpdateAccount formUpdateAccount);
    AccountDto login(FormLoginDto formLoginDto);
    void deleteById(Long id);
    boolean existByEmail(String email);

}
