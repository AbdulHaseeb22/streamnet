package com.streamnet.service.impl;

import com.streamnet.repository.TagRepository;
import com.streamnet.service.TagClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagClientServiceImpl implements TagClientService {

    private final TagRepository tagRepository;

    @Override
    public List<String> getTagsByText(String text) {
        return tagRepository.getTagsByText(text);
    }
}
