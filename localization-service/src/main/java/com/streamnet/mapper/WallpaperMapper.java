package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.WallpaperResponse;
import com.streamnet.model.Wallpaper;
import com.streamnet.service.WallpaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WallpaperMapper {

    private final BasicMapper basicMapper;
    private final WallpaperService wallpaperService;

    public List<WallpaperResponse> getWallpapers() {
        List<Wallpaper> wallpapers = wallpaperService.getWallpapers();
        return basicMapper.convertToResponseList(wallpapers, WallpaperResponse.class);
    }

    public WallpaperResponse getWallpaper() {
        Wallpaper wallpaper = wallpaperService.getWallpaper();
        return basicMapper.convertToResponse(wallpaper, WallpaperResponse.class);
    }
}
