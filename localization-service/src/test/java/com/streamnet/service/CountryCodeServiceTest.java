package com.streamnet.service;

import com.streamnet.model.CountryCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CountryCodeServiceTest extends AbstractServiceTest {

    @Autowired
    private CountryCodeService countryCodeService;

    @Test
    public void getCountryCodes_ShouldReturnCountryCodes() {
        List<CountryCode> countryCodes = List.of(new CountryCode(), new CountryCode());
        when(countryCodeRepository.findAll()).thenReturn(countryCodes);
        assertEquals(countryCodes, countryCodeService.getCountryCodes());
        verify(countryCodeRepository, times(1)).findAll();
    }
}
