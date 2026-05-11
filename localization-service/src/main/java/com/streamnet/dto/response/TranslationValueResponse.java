package com.streamnet.dto.response;

import com.streamnet.model.LanguageCode;
import lombok.Data;

@Data
public class TranslationValueResponse {
    private Long id;
    private LanguageCode languageCode;
    private String value;
}
