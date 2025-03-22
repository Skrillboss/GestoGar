package com.heredi.nowait.domain.householdChore.model;

import com.heredi.nowait.domain.home.model.Home;
import com.heredi.nowait.domain.queue.model.Queue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class householdChore {

    private Long id;
    private String name;
    private String description;
    private Double rating;
    private String mainImagePath;
    private String secondaryImagePath;
    private ItemStatus status;
    private Home home;
    private Queue queue;

    public enum ItemStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        NO_STOCK,
        UNHANDLED_ERROR
    }
}
