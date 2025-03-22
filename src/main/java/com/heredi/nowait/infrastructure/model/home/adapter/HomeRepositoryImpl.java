package com.heredi.nowait.infrastructure.model.home.adapter;

import com.heredi.nowait.application.exception.AppErrorCode;
import com.heredi.nowait.application.exception.AppException;
import com.heredi.nowait.domain.home.model.Home;
import com.heredi.nowait.domain.home.port.HomeRepository;
import com.heredi.nowait.infrastructure.model.home.entity.HomeEntity;
import com.heredi.nowait.infrastructure.model.home.jpa.HomeJPARepository;
import com.heredi.nowait.infrastructure.model.home.mapper.HomeEntityMapper;
import com.heredi.nowait.infrastructure.model.user.entity.UserEntity;
import com.heredi.nowait.infrastructure.model.user.jpa.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HomeRepositoryImpl implements HomeRepository {
    private final HomeJPARepository homeJPARepository;

    private final UserJPARepository userJPARepository;

    @Autowired
    private HomeEntityMapper homeEntityMapper;

    HomeRepositoryImpl(@Lazy HomeJPARepository homeJPARepository, UserJPARepository userJPARepository) {
        this.homeJPARepository = homeJPARepository;
        this.userJPARepository = userJPARepository;
    }

    @Override
    public Home createHome(Home home) {
        HomeEntity homeEntity = this.homeEntityMapper.toHomeEntity(home);
        HomeEntity savedHomeEntity = this.homeJPARepository.save(homeEntity);

        return this.homeEntityMapper.toHome(savedHomeEntity);
    }

    @Override
    public Home getHome(Long businessId) {
        HomeEntity homeEntity = this.homeJPARepository.findById(businessId).
                orElseThrow(() -> new AppException(
                        AppErrorCode.BUSINESS_NOT_FOUND_BY_ID,
                        "getBusiness",
                        "businessId: " + businessId,
                        HttpStatus.NOT_FOUND
                ));

        return this.homeEntityMapper.toHome(homeEntity);
    }

    @Override
    public Home updateHome(Long userId, Home home) {
        HomeEntity homeEntity = this.homeJPARepository.findById(home.getId())
                .orElseThrow(() -> new AppException(
                        AppErrorCode.BUSINESS_NOT_FOUND_BY_ID,
                        "updateBusiness",
                        "businessId: " + home.getId(),
                        HttpStatus.NOT_FOUND
                ));

        UserEntity userEntity = this.userJPARepository.getReferenceById(userId);

        if(!userEntity.getHomeEntity().getId().equals(homeEntity.getId())){
            throw new AppException(
                    AppErrorCode.BUSINESS_DOES_NOT_BELONG_TO_USER,
                    "updateBusiness",
                    "userId: " + userId + "BusinessId: " + homeEntity.getId(),
                    HttpStatus.FORBIDDEN);
        }
        homeEntity.setName(home.getName());
        homeEntity.setImageUrl(home.getImageUrl());
        homeEntity.setPhone(home.getPhone());
        homeEntity.setAddress(home.getAddress());
        homeEntity.setEmail(home.getEmail());

        return this.homeEntityMapper.toHome(this.homeJPARepository.save(homeEntity));
    }
}