package com.streamnet.dto.request;

import com.streamnet.constants.Regexp;
import com.streamnet.constants.UserErrorMessage;
import lombok.Data;

import jakarta.validation.constraints.Email;

@Data
public class ProcessEmailRequest {
    @Email(regexp = Regexp.USER_EMAIL_REGEXP, message = UserErrorMessage.EMAIL_NOT_VALID)
    private String email;
}
