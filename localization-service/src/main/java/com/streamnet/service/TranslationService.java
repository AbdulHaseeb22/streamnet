package com.streamnet.service;

import com.streamnet.dto.request.TranslationRequest;
import com.streamnet.model.Translation;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface TranslationService {

    List<Translation> getTranslations();

    Translation getTranslation(String translationKey);

    Translation createTranslation(TranslationRequest request, BindingResult bindingResult);

    Translation updateTranslation(String translationKey, TranslationRequest request, BindingResult bindingResult);

    String deleteTranslation(String translationKey);
}
