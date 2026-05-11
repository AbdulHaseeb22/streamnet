package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.CountryCodeResponse;
import com.streamnet.model.CountryCode;
import com.streamnet.service.CountryCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CountryCodeMapperTest {

    @InjectMocks
    private CountryCodeMapper countryCodeMapper;

    @Mock
    private CountryCodeService countryCodeService;

    @Mock
    private BasicMapper basicMapper;

    @Test
    public void getCountryCodes() {
        List<CountryCode> countryCodes = List.of(new CountryCode(), new CountryCode());
        List<CountryCodeResponse> countryCodeResponses = List.of(new CountryCodeResponse(), new CountryCodeResponse());
        when(countryCodeService.getCountryCodes()).thenReturn(countryCodes);
        when(basicMapper.convertToResponseList(countryCodes, CountryCodeResponse.class)).thenReturn(countryCodeResponses);
        assertEquals(countryCodeResponses, countryCodeMapper.getCountryCodes());
        verify(countryCodeService, times(1)).getCountryCodes();
        verify(basicMapper, times(1)).convertToResponseList(countryCodes, CountryCodeResponse.class);
    }
}
