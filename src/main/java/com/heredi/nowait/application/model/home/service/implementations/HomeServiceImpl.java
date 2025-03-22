package com.heredi.nowait.application.model.home.service.implementations;

import com.heredi.nowait.application.model.home.dto.in.HomeRequestDTO;
import com.heredi.nowait.application.model.home.dto.out.HomeResponseDTO;
import com.heredi.nowait.application.model.home.mapper.HomeMapper;
import com.heredi.nowait.application.model.home.service.interfaces.HomeService;
import com.heredi.nowait.domain.home.model.Home;
import com.heredi.nowait.domain.home.port.HomeRepository;
import com.heredi.nowait.domain.user.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    private final HomeRepository homeRepository;

    private final UserRepository userRepository;
    @Autowired
    private final HomeMapper homeMapper;

    public HomeServiceImpl(HomeRepository homeRepository, UserRepository userRepository, HomeMapper homeMapper) {
        this.homeRepository = homeRepository;
        this.userRepository = userRepository;
        this.homeMapper = homeMapper;
    }

    @Override
    public HomeResponseDTO createHome(HomeRequestDTO homeRequestDTO) {
        Home home = this.homeRepository.createHome(homeMapper.toHome(homeRequestDTO));
        return this.homeMapper.toHomeDTO(home);
    }

    @Override
    public HomeResponseDTO getBusiness(String businessId) {
        Home home = this.homeRepository.getHome(Long.parseLong(businessId));
        return this.homeMapper.toHomeDTO(home);
    }

    @Override
    public HomeResponseDTO updateBusiness(String businessId, HomeRequestDTO homeRequestDTO, Long userId) {
        Home home = this.homeMapper.toHome(homeRequestDTO);
        home.setId(Long.parseLong(businessId));
        return this.homeMapper.toHomeDTO(this.homeRepository.updateHome(userId, home));
    }
}