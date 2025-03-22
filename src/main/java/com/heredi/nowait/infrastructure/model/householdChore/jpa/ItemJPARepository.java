package com.heredi.nowait.infrastructure.model.householdChore.jpa;
import com.heredi.nowait.infrastructure.model.householdChore.entity.householdChoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJPARepository extends JpaRepository<householdChoreEntity, Long> {
}
