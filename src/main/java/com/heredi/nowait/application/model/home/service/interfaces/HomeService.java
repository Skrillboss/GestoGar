package com.heredi.nowait.application.model.home.service.interfaces;

import com.heredi.nowait.application.model.home.dto.in.HomeRequestDTO;
import com.heredi.nowait.application.model.home.dto.out.HomeResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface HomeService {
    HomeResponseDTO getBusiness(String businessId);

    HomeResponseDTO updateBusiness(String businessId, HomeRequestDTO homeRequestDTO, Long userId);

    HomeResponseDTO createHome(HomeRequestDTO homeRequestDTO);
}
