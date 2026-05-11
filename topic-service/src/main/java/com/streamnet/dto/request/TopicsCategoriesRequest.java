package com.streamnet.dto.request;

import com.streamnet.commons.enums.TopicCategory;
import lombok.Data;

import java.util.List;

@Data
public class TopicsCategoriesRequest {
    private List<TopicCategory> categories;
}
