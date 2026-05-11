package com.streamnet.repository;

import com.streamnet.model.GifImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GifImageRepository extends JpaRepository<GifImage, Long> {
}
