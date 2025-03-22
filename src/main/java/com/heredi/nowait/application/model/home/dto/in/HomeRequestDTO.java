package com.heredi.nowait.application.model.home.dto.in;

import com.heredi.nowait.application.model.item.dto.in.ItemRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class HomeRequestDTO {

    private String name;
    private String imageUrl;
    private String phone;
    private String address;
    private String email;
    private List<ItemRequestDTO> items;
}