package com.streamnet.service;

import com.streamnet.repository.CountryCodeRepository;
import com.streamnet.repository.GifImageRepository;
import com.streamnet.repository.LanguageRepository;
import com.streamnet.repository.WallpaperRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractServiceTest {

    @MockBean
    CountryCodeRepository countryCodeRepository;

    @MockBean
    GifImageRepository gifImageRepository;

    @MockBean
    LanguageRepository languageRepository;

    @MockBean
    WallpaperRepository wallpaperRepository;
}
