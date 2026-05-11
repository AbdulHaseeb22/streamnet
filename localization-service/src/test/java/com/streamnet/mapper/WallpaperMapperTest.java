package com.streamnet.mapper;

import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.WallpaperResponse;
import com.streamnet.model.Wallpaper;
import com.streamnet.service.WallpaperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class WallpaperMapperTest {

    @InjectMocks
    private WallpaperMapper wallpaperMapper;

    @Mock
    private WallpaperService wallpaperService;

    @Mock
    private BasicMapper basicMapper;

    @Test
    public void getWallpapers() {
        List<Wallpaper> wallpapers = List.of(new Wallpaper(), new Wallpaper());
        List<WallpaperResponse> wallpaperResponses = List.of(new WallpaperResponse(), new WallpaperResponse());
        when(wallpaperService.getWallpapers()).thenReturn(wallpapers);
        when(basicMapper.convertToResponseList(wallpapers, WallpaperResponse.class)).thenReturn(wallpaperResponses);
        assertEquals(wallpaperResponses, wallpaperMapper.getWallpapers());
        verify(wallpaperService, times(1)).getWallpapers();
        verify(basicMapper, times(1)).convertToResponseList(wallpapers, WallpaperResponse.class);
    }
}
