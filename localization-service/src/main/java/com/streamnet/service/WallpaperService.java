package com.streamnet.service;

import com.streamnet.model.Wallpaper;

import java.util.List;

public interface WallpaperService {
    List<Wallpaper> getWallpapers();

    Wallpaper getWallpaper();
}
