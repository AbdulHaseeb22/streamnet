package com.streamnet.service.job;

import com.streamnet.client.ListsClient;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListsServiceFetchUsersJob implements Job {

    private final ListsClient listsClient;

    @Override
    public void execute(JobExecutionContext context) {
        listsClient.runImportUsersBatchJob();
    }
}
