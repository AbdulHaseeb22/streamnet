package com.streamnet.client;

import com.streamnet.commons.configuration.FeignConfiguration;
import com.streamnet.commons.constants.FeignConstants;
import com.streamnet.commons.constants.PathConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = FeignConstants.TOPIC_SERVICE, path = PathConstants.API_V1_TOPICS, configuration = FeignConfiguration.class)
public interface TopicClient {

    @PostMapping(PathConstants.USER_BATCH_JOB)
    void runImportUsersBatchJob();
}
