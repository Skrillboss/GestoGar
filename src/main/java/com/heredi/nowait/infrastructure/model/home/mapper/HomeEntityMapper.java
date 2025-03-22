package com.heredi.nowait.infrastructure.model.home.mapper;

import com.heredi.nowait.domain.home.model.Home;
import com.heredi.nowait.infrastructure.model.home.entity.HomeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface HomeEntityMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "householdChoresEntity", target = "householdChores"),
    })
    Home toHome(HomeEntity homeEntity);

    @InheritInverseConfiguration
    HomeEntity toHomeEntity(Home home);
}
