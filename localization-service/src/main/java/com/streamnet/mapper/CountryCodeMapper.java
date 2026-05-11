package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.CountryCodeResponse;
import com.streamnet.model.CountryCode;
import com.streamnet.service.CountryCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryCodeMapper {

    private final BasicMapper basicMapper;
    private final CountryCodeService countryCodeService;

    public List<CountryCodeResponse> getCountryCodes() {
        List<CountryCode> countryCodes = countryCodeService.getCountryCodes();
        return basicMapper.convertToResponseList(countryCodes, CountryCodeResponse.class);
    }
}
