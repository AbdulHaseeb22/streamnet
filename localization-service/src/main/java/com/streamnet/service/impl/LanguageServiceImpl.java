package com.streamnet.service.impl;

import com.streamnet.model.Language;
import com.streamnet.repository.LanguageRepository;
import com.streamnet.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }
}
