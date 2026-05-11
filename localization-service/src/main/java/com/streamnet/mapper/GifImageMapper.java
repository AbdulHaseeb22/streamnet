package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.GifImageResponse;
import com.streamnet.model.GifImage;
import com.streamnet.service.GifImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GifImageMapper {

    private final BasicMapper basicMapper;
    private final GifImageService gifImageService;

    public List<GifImageResponse> getGifImages() {
        List<GifImage> gifImages = gifImageService.getGifImages();
        return basicMapper.convertToResponseList(gifImages, GifImageResponse.class);
    }
}
