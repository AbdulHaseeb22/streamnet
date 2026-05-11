package com.streamnet.dto.response;

import lombok.Data;

@Data
public class CountryCodeResponse {
    private Long id;
    private String countryCode;
    private String phoneCode;
    private String country;
}
