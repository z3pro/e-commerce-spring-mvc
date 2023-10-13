package ra.dao;

import ra.model.domain.Account;

public interface IAccountDao extends IGenericDao<Account, Long> {

  Account login(String username, String password);
}
