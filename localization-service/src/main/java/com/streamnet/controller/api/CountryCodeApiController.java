package com.streamnet.controller.api;

import com.streamnet.commons.constants.PathConstants;
import com.streamnet.service.CountryCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.API_V1_LOCALIZATION)
public class CountryCodeApiController {

    private final CountryCodeService countryCodeService;

    @GetMapping(PathConstants.PHONE_CODE)
    public Boolean isPhoneCodeExists(@PathVariable("code") String code) {
        return countryCodeService.isPhoneCodeExists(code);
    }
}
