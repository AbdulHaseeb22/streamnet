package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.CountryCodeResponse;
import com.streamnet.dto.response.GifImageResponse;
import com.streamnet.model.CountryCode;
import com.streamnet.model.GifImage;
import com.streamnet.service.CountryCodeService;
import com.streamnet.service.GifImageService;
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
public class GifImageMapperTest {

    @InjectMocks
    private GifImageMapper countryCodeMapper;

    @Mock
    private GifImageService countryCodeService;

    @Mock
    private BasicMapper basicMapper;

    @Test
    public void getGifImages() {
        List<GifImage> gifImages = List.of(new GifImage(), new GifImage());
        List<GifImageResponse> gifImageResponses = List.of(new GifImageResponse(), new GifImageResponse());
        when(countryCodeService.getGifImages()).thenReturn(gifImages);
        when(basicMapper.convertToResponseList(gifImages, GifImageResponse.class)).thenReturn(gifImageResponses);
        assertEquals(gifImageResponses, countryCodeMapper.getGifImages());
        verify(countryCodeService, times(1)).getGifImages();
        verify(basicMapper, times(1)).convertToResponseList(gifImages, GifImageResponse.class);
    }
}
