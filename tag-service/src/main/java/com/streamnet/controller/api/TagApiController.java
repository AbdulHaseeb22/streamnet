package com.streamnet.controller.api;

import com.streamnet.commons.constants.PathConstants;
import com.streamnet.service.TagClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.API_V1_TAGS)
public class TagApiController {

    private final TagClientService tagClientService;

    @GetMapping(PathConstants.SEARCH_TEXT)
    public List<String> getTagsByText(@PathVariable("text") String text) {
        return tagClientService.getTagsByText(text);
    }
}
