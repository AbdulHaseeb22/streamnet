package com.streamnet.controller.api;

import com.streamnet.commons.constants.PathConstants;
import com.streamnet.dto.response.WallpaperResponse;
import com.streamnet.mapper.WallpaperMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.API_V1_LOCALIZATION)
public class WallpaperApiController {

    private final WallpaperMapper wallpaperMapper;

    @GetMapping(PathConstants.WALLPAPER)
    public WallpaperResponse getWallpaper() {
        return wallpaperMapper.getWallpaper();
    }
}
