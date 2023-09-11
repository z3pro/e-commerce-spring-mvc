package ra.model.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ra.model.domain.Account;




public class FormRegisterDto {
    @Size(min = 6,message = "FullName must be more than 6 character!")
    private String fullName;
    @Pattern(regexp = "c",message = "This email is invalid!")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password is invalid!")
    private String password;
    private String rePassword;
    @Pattern(regexp = "^0[0-9]{9,10}$",message = "Phone is invalid")
    private String phone;

    public FormRegisterDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Account mapperToDomain(){
        Account account = new Account(fullName,email,password,phone);
        return account;
    }
}
