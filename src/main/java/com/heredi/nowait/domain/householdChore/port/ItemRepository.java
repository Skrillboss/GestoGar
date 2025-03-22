package com.heredi.nowait.domain.householdChore.port;

import com.heredi.nowait.domain.householdChore.model.householdChore;

public interface ItemRepository {
    householdChore create(Long businessId, householdChore householdChore);
    householdChore getItemById(Long itemId);
}
