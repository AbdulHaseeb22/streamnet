package com.streamnet.service.impl;

import com.streamnet.model.Wallpaper;
import com.streamnet.repository.WallpaperRepository;
import com.streamnet.service.WallpaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WallpaperServiceImpl implements WallpaperService {

    private final WallpaperRepository wallpaperRepository;

    @Override
    public List<Wallpaper> getWallpapers() {
        return wallpaperRepository.findAll();
    }

    @Override
    public Wallpaper getWallpaper() {
        return wallpaperRepository.getWallpaper();
    }
}
