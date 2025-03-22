package com.heredi.nowait.infrastructure.model.householdChore.controller;

import com.google.zxing.WriterException;
import com.heredi.nowait.application.model.item.dto.in.ItemRequestDTO;
import com.heredi.nowait.application.model.item.dto.out.HouseholdChoresResponseDTO;
import com.heredi.nowait.application.model.item.service.interfaces.HouseholdChoresService;
import com.heredi.nowait.infrastructure.auth.jwt.AuthJwtImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/item")
public class HouseholdChoresController {

    private final HouseholdChoresService householdChoresService;

    private final AuthJwtImpl authJwt;

    @Autowired
    HouseholdChoresController(HouseholdChoresService householdChoresService, AuthJwtImpl authJwt){
        this.householdChoresService = householdChoresService;
        this.authJwt = authJwt;
    }

    @PostMapping("/create")
    public ResponseEntity<HouseholdChoresResponseDTO> createHouseholdChores(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ItemRequestDTO itemRequestDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        Long userId = authJwt.extractUserId(accessToken);
        return new ResponseEntity<HouseholdChoresResponseDTO>(householdChoresService.create(userId, itemRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<HouseholdChoresResponseDTO> getHouseholdChores(@PathVariable String itemId) {
        return new ResponseEntity<HouseholdChoresResponseDTO>(householdChoresService.get(itemId), HttpStatus.OK);
    }

    @PostMapping("/{itemId}/sendQR/mail")
    public void sendQRToMail(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String itemId) throws MessagingException, IOException, WriterException, NoSuchAlgorithmException, InvalidKeySpecException {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        Long userId = authJwt.extractUserId(accessToken);

        householdChoresService.saveItemIdQrToMail(userId, itemId);
    }
}