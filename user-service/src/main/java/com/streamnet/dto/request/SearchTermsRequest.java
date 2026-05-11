package com.streamnet.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchTermsRequest {
    private List<Long> users;
}
