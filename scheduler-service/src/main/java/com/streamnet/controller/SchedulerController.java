package com.streamnet.controller;

import com.streamnet.commons.constants.PathConstants;
import com.streamnet.dto.response.JobDetailResponse;
import com.streamnet.dto.request.JobRequest;
import com.streamnet.dto.response.TriggerResponse;
import com.streamnet.mapper.SchedulerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.API_V1_SCHEDULER)
public class SchedulerController {

    private final SchedulerMapper schedulerMapper;

    @GetMapping(PathConstants.JOBS)
    public ResponseEntity<List<JobDetailResponse>> getJobs() {
        return ResponseEntity.ok(schedulerMapper.getJobs());
    }

    @GetMapping(PathConstants.JOBS_NAME_GROUP_NAME)
    public ResponseEntity<JobDetailResponse> getJob(@PathVariable("jobName") String jobName,
                                                    @PathVariable("groupName") String groupName) {
        return ResponseEntity.ok(schedulerMapper.getJob(jobName, groupName));
    }

    @GetMapping(PathConstants.JOBS_TRIGGERS_NAME_GROUP_NAME)
    public ResponseEntity<List<TriggerResponse>> getJobTriggers(@PathVariable("jobName") String jobName,
                                                                @PathVariable("groupName") String groupName) {
        return ResponseEntity.ok(schedulerMapper.getJobTriggers(jobName, groupName));
    }

    @PostMapping(PathConstants.JOBS_CREATE)
    public ResponseEntity<TriggerResponse> createJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(schedulerMapper.createJob(jobRequest));
    }

    @PutMapping(PathConstants.JOBS_UPDATE)
    public ResponseEntity<TriggerResponse> updateJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(schedulerMapper.updateJob(jobRequest));
    }

    @DeleteMapping(PathConstants.JOBS_DELETE)
    public ResponseEntity<String> deleteJob(@PathVariable("jobName") String jobName,
                                            @PathVariable("groupName") String groupName) {
        return ResponseEntity.ok(schedulerMapper.deleteJob(jobName, groupName));
    }
}
