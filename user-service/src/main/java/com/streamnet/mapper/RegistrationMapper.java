package com.streamnet.mapper;

import com.streamnet.dto.request.EndRegistrationRequest;
import com.streamnet.dto.request.RegistrationRequest;
import com.streamnet.dto.response.AuthenticationResponse;
import com.streamnet.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {

    private final RegistrationService registrationService;
    private final AuthenticationMapper authenticationMapper;

    public String registration(RegistrationRequest request, BindingResult bindingResult) {
        return registrationService.registration(request, bindingResult);
    }

    public String sendRegistrationCode(String email, BindingResult bindingResult) {
        return registrationService.sendRegistrationCode(email, bindingResult);
    }

    public String checkRegistrationCode(String code) {
        return registrationService.checkRegistrationCode(code);
    }

    public AuthenticationResponse endRegistration(EndRegistrationRequest request, BindingResult bindingResult) {
        return authenticationMapper.getAuthenticationResponse(
                registrationService.endRegistration(request.getEmail(), request.getPassword(), bindingResult));
    }
}
