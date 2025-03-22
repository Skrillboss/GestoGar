package com.heredi.nowait.application.model.home.mapper;

import com.heredi.nowait.application.model.home.dto.in.HomeRequestDTO;
import com.heredi.nowait.application.model.home.dto.out.HomeResponseDTO;
import com.heredi.nowait.application.model.item.mapper.ItemMapper;
import com.heredi.nowait.domain.home.model.Home;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HomeMapper {

    private final ItemMapper itemMapper;

    public HomeMapper(ItemMapper itemMapper){
        this.itemMapper = itemMapper;
    }

    public HomeResponseDTO toHomeDTO(Home home) {
        if (home == null) {
            return null;
        }

        HomeResponseDTO dto = new HomeResponseDTO();
        dto.setId(home.getId().toString());
        dto.setName(home.getName());
        dto.setImageUrl(home.getImageUrl());
        dto.setPhone(home.getPhone());
        dto.setAddress(home.getAddress());
        dto.setEmail(home.getEmail());
        dto.setCreatedAt(home.getCreatedAt());
        dto.setItems(itemMapper.toItemsDTO(home.getHouseholdChores()));

        return dto;
    }

    public Home toHome(HomeRequestDTO homeDTO) {
        if (homeDTO == null) {
            return null;
        }

        Home home = new Home();
        home.setName(homeDTO.getName());
        home.setImageUrl(homeDTO.getImageUrl());
        home.setPhone(homeDTO.getPhone());
        home.setAddress(homeDTO.getAddress());
        home.setEmail(homeDTO.getEmail());
        home.setCreatedAt(LocalDate.now().toString());
        home.setHouseholdChores(itemMapper.toItems(homeDTO.getItems()));

        return home;
    }
}
