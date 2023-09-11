package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.IAccountDao;
import ra.model.domain.Account;
import ra.model.dto.request.FormLoginDto;
import ra.model.dto.request.FormRegisterDto;
import ra.model.dto.request.FormUpdateAccount;
import ra.model.dto.response.AccountDto;
import ra.service.IAccountService;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    @Override
    public List<AccountDto> getAll() {
//        List<AccountDto> listDto = new ArrayList<>();
//        List<Account> list = accountDao.findAll();
//        for (Account a:list) {
//            AccountDto accountDto =  AccountDto.mapperDomainToDto(a);
//            listDto.add(accountDto);
//        }
        // cú pháp java8

//        List<AccountDto> list = accountDao.findAll().stream()
//                .map(AccountDto::mapperDomainToDto)
//                .collect(Collectors.toList());
        return accountDao.findAll().stream()
                .map(AccountDto::mapperDomainToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Long id) {
        Account account =accountDao.findById(id);
        if (account==null)
            return null;
        return AccountDto.mapperDomainToDto(account);
    }

    @Override
    public void register(FormRegisterDto formRegisterDto) {
        accountDao.save(formRegisterDto.mapperToDomain());
    }

    @Override
    public void updateAccountInfo(FormUpdateAccount formUpdateAccount) {

    }

    @Override
    public AccountDto login(FormLoginDto formLoginDto) {
        Account account = accountDao.login(formLoginDto.getUsername(), formLoginDto.getPassword());
        if (account==null)
            return null;
        return AccountDto.mapperDomainToDto(account);
    }

    @Override
    public void deleteById(Long id) {
        accountDao.delete(id);
    }
    @Override
    public boolean existByEmail(String email){
        for (AccountDto acc: getAll()
             ) {
            if(acc.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
}
