package com.heredi.nowait.application.model.item.service.interfaces;

import com.google.zxing.WriterException;
import com.heredi.nowait.application.model.item.dto.in.ItemRequestDTO;
import com.heredi.nowait.application.model.item.dto.out.HouseholdChoresResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface HouseholdChoresService {
    HouseholdChoresResponseDTO create(Long userId, ItemRequestDTO itemRequestDTO);
    void saveItemIdQrToMail(Long userId, String itemId) throws IOException, WriterException, MessagingException;
    HouseholdChoresResponseDTO get(String itemId);
}
