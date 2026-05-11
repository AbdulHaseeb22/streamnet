package com.streamnet.dto.request;

import com.streamnet.constants.LocalizationErrorMessage;
import com.streamnet.model.LanguageCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TranslationRequest {

    @NotBlank(message = LocalizationErrorMessage.EMPTY_TRANSLATION_KEY)
    private String translationKey;

    @Valid
    @Size(min = LanguageCode.SIZE, max = LanguageCode.SIZE, message = LocalizationErrorMessage.EMPTY_TRANSLATION_VALUES)
    private List<TranslationValueRequest> translationValues;
}
