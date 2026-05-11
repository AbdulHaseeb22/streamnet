package com.streamnet.controller.rest;

import com.streamnet.commons.constants.PathConstants;
import com.streamnet.dto.response.LanguagesResponse;
import com.streamnet.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.UI_V1_LOCALIZATION)
public class LanguageController {

    private final LanguageMapper getGifImages;

    @GetMapping(PathConstants.LANGUAGES)
    public ResponseEntity<List<LanguagesResponse>> getLanguages() {
        return ResponseEntity.ok(getGifImages.getLanguages());
    }
}
