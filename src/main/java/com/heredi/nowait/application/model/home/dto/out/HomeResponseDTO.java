package com.heredi.nowait.application.model.home.dto.out;
import com.heredi.nowait.application.model.item.dto.out.HouseholdChoresResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponseDTO {

    private String id;
    private String name;
    private String imageUrl;
    private String phone;
    private String address;
    private String email;
    private String createdAt;
    private List<HouseholdChoresResponseDTO> items;
}