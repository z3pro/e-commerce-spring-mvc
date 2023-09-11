package ra.exception;

import ra.model.dto.request.FormLoginDto;

public class UserNotFoundException extends Exception{
    private FormLoginDto formLoginDto;
    public UserNotFoundException(String message,FormLoginDto formLoginDto) {
        super(message);
        this.formLoginDto=formLoginDto;
    }

    public FormLoginDto getFormLoginDto() {
        return formLoginDto;
    }

    public void setFormLoginDto(FormLoginDto formLoginDto) {
        this.formLoginDto = formLoginDto;
    }
}
