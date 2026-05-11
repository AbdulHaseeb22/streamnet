package com.streamnet.repository.projection;

import java.time.LocalDateTime;

public interface RetweetProjection {
    LocalDateTime getRetweetDate();
    TweetUserProjection getTweet();
}
