package com.streamnet.service.job;

import com.streamnet.client.TweetClient;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetServiceFetchUsersJob implements Job {

    private final TweetClient tweetClient;

    @Override
    public void execute(JobExecutionContext context) {
        tweetClient.runImportUsersBatchJob();
    }
}
