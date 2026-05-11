package com.streamnet.dto.response;

import com.streamnet.commons.enums.TopicCategory;
import com.streamnet.repository.projection.TopicProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicsByCategoriesResponse {
    private TopicCategory topicCategory;
    private List<TopicProjection> topicsByCategories;
}
