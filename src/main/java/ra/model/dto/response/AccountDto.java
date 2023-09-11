package ra.model.dto.response;

import ra.model.domain.Account;

public class AccountDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String avatarUrl;
    private boolean role = false;
    private boolean status=true;

    public AccountDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public static AccountDto mapperDomainToDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.id = account.getId();
        accountDto.fullName = account.getFullName();
        accountDto.email = account.getEmail();
        accountDto.phone = account.getPhone();
        accountDto.address = account.getAddress();
        accountDto.avatarUrl = account.getAvatarUrl();
        accountDto.role = account.isRole();
        accountDto.status = account.isStatus();
        return accountDto;
    }


}
