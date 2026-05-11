package com.streamnet.service;

import com.streamnet.model.CountryCode;

import java.util.List;

public interface CountryCodeService {
    List<CountryCode> getCountryCodes();

    boolean isPhoneCodeExists(String phoneCode);
}
