package com.streamnet.repository.projection;

import java.time.LocalDateTime;

public interface BookmarkProjection {
    LocalDateTime getBookmarkDate();
    TweetProjection getTweet();
}
