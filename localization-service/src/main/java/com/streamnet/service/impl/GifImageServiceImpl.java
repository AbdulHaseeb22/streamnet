package com.streamnet.service.impl;

import com.streamnet.model.GifImage;
import com.streamnet.repository.GifImageRepository;
import com.streamnet.service.GifImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GifImageServiceImpl implements GifImageService {

    private final GifImageRepository gifImageRepository;

    @Override
    public List<GifImage> getGifImages() {
        return gifImageRepository.findAll();
    }
}
