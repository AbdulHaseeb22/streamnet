package com.streamnet.repository;

import com.streamnet.model.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WallpaperRepository extends JpaRepository<Wallpaper, Long> {

    @Query("SELECT wallpaper FROM Wallpaper wallpaper ORDER BY RANDOM() LIMIT 1")
    Wallpaper getWallpaper();
}
