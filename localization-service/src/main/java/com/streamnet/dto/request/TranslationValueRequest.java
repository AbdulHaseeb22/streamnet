package com.streamnet.dto.request;

import com.streamnet.constants.LocalizationErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TranslationValueRequest {

    @NotBlank(message = LocalizationErrorMessage.EMPTY_LANGUAGE_CODE)
    private String languageCode;

    @NotBlank(message = LocalizationErrorMessage.EMPTY_LANGUAGE_VALUE)
    private String value;
}
