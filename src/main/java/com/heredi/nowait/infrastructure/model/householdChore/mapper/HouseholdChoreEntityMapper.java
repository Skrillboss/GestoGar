package com.heredi.nowait.infrastructure.model.householdChore.mapper;

import com.heredi.nowait.domain.householdChore.model.householdChore;
import com.heredi.nowait.infrastructure.model.householdChore.entity.householdChoreEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface HouseholdChoreEntityMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "rating", target = "rating"),
            @Mapping(source = "mainImagePath", target = "mainImagePath"),
            @Mapping(source = "secondaryImagePath", target = "secondaryImagePath"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "homeEntity", target = "home"),
            @Mapping(source = "queue", target = "queue"),
    })
    householdChore toHouseholdChore(householdChoreEntity householdChoreEntity);

    @InheritInverseConfiguration
    householdChoreEntity toHouseholdChoreEntity(householdChore householdChore);
}
