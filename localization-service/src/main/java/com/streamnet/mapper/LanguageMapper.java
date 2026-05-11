package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.LanguagesResponse;
import com.streamnet.model.Language;
import com.streamnet.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanguageMapper {

    private final BasicMapper basicMapper;
    private final LanguageService languageService;

    public List<LanguagesResponse> getLanguages() {
        List<Language> languages = languageService.getLanguages();
        return basicMapper.convertToResponseList(languages, LanguagesResponse.class);
    }
}
