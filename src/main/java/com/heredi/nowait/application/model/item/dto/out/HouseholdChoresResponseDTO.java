package com.heredi.nowait.application.model.item.dto.out;

import com.heredi.nowait.application.model.queue.dto.out.QueueResponseDTO;
import lombok.Data;

@Data
public class HouseholdChoresResponseDTO {

    private String id;
    private String name;
    private String description;
    private String businessId;
    private String businessName;
    private Double rating;
    private String mainImagePath;
    private String secondaryImagePath;
    private String status;
    private QueueResponseDTO queueResponseDTO;
}
