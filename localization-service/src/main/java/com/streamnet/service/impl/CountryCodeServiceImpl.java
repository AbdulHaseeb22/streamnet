package com.streamnet.service.impl;

import com.streamnet.model.CountryCode;
import com.streamnet.repository.CountryCodeRepository;
import com.streamnet.service.CountryCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryCodeServiceImpl implements CountryCodeService {

    private final CountryCodeRepository countryCodeRepository;

    @Override
    public List<CountryCode> getCountryCodes() {
        return countryCodeRepository.findAll();
    }

    @Override
    public boolean isPhoneCodeExists(String phoneCode) {
        return countryCodeRepository.isPhoneCodeExists(phoneCode);
    }
}
