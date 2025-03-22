package com.heredi.nowait.infrastructure.model.home.controller;

import com.heredi.nowait.application.model.home.dto.in.HomeRequestDTO;
import com.heredi.nowait.application.model.home.dto.out.HomeResponseDTO;
import com.heredi.nowait.application.model.home.service.interfaces.HomeService;
import com.heredi.nowait.infrastructure.auth.jwt.AuthJwtImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/business")
public class HomeController {

    private final HomeService homeService;

    private final AuthJwtImpl authJwt;

    @Autowired
    HomeController(HomeService homeService, AuthJwtImpl authJwt, AuthJwtImpl authJwt1){
        this.homeService = homeService;
        this.authJwt = authJwt1;
    }

    @PostMapping("/register")
    public ResponseEntity<HomeResponseDTO> createHome(@RequestBody HomeRequestDTO homeRequestDTO) {
        return new ResponseEntity<HomeResponseDTO>(homeService.createHome(homeRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<HomeResponseDTO> getBusiness(@PathVariable String homeId){
        return new ResponseEntity<HomeResponseDTO>(this.homeService.getBusiness(homeId), HttpStatus.OK);
    }

    @PatchMapping("/update/{homeId}")
    public ResponseEntity<HomeResponseDTO> updateBusiness(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String homeId, @RequestBody HomeRequestDTO homeRequestDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        Long userId = authJwt.extractUserId(accessToken);
        return new ResponseEntity<HomeResponseDTO>(this.homeService.updateBusiness(homeId, homeRequestDTO, userId), HttpStatus.OK);
    }
}