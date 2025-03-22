package com.heredi.nowait.application.model.item.service.implementations;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.heredi.nowait.application.exception.AppErrorCode;
import com.heredi.nowait.application.exception.AppException;
import com.heredi.nowait.application.model.email.dto.EmailDTO;
import com.heredi.nowait.application.model.email.service.interfaces.MailSenderService;
import com.heredi.nowait.application.model.item.dto.in.ItemRequestDTO;
import com.heredi.nowait.application.model.item.dto.out.HouseholdChoresResponseDTO;
import com.heredi.nowait.application.model.item.mapper.ItemMapper;
import com.heredi.nowait.application.model.item.service.interfaces.HouseholdChoresService;
import com.heredi.nowait.domain.householdChore.model.householdChore;
import com.heredi.nowait.domain.householdChore.port.ItemRepository;
import com.heredi.nowait.domain.user.model.Users;
import com.heredi.nowait.domain.user.port.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.heredi.nowait.application.utility.QrGeneratorService.createQR;

@Service
public class HouseholdChoresServiceImpl implements HouseholdChoresService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private final ItemMapper itemMapper;

    public HouseholdChoresServiceImpl(ItemRepository itemRepository, UserRepository userRepository, ItemMapper itemMapper){
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional
    @Override
    public HouseholdChoresResponseDTO create(Long userId, ItemRequestDTO itemRequestDTO) {
        Long businessId = this.userRepository.getUserById(userId).getHome().getId();
        householdChore householdChore = itemRepository.create(businessId, itemMapper.toItem(itemRequestDTO));

        return itemMapper.toItemResponseDTO(householdChore);
    }

    @Transactional
    @Override
    public void saveItemIdQrToMail(Long userId, String itemId) throws IOException, WriterException, MessagingException {
        Users user = this.userRepository.getUserById(userId);
        householdChore householdChore = this.itemRepository.getItemById(Long.parseLong(itemId));

        boolean userHasItem = user.getHome().getId().equals(
                householdChore.getHome().getId()
        );

        if(!userHasItem){
            throw new AppException(
                    AppErrorCode.ITEM_NOT_FOUND_IN_BUSINESS,
                    "saveItemIdQrToMail",
                    "ItemId: " + itemId + " BusinessId: " + user.getHome().getId(),
                    HttpStatus.FORBIDDEN
            );
        }

        try {

            String path = "itemId.png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            createQR(itemId, path, charset, hashMap, 200, 200);
            File file = new File(path);
            EmailDTO emailDTO = new EmailDTO(
                    user.getHome().getEmail(),
                    "NoWait: Id " + householdChore.getName(),
                    "Item description: " + householdChore.getDescription() +"\n \nEste es el QR generado, te recomendamos " +
                            "Imprimirlo y colocarlo en el lugar donde proporcionaras el producto o servicio",
                    user.getNickName(),
                    itemId,
                    file
            );
            mailSenderService.sendNewMail("qr", emailDTO);
        } catch (Exception e) {
            throw new AppException(
                    AppErrorCode.EMAIL_SENDING_FAILED,
                    "saveItemIdQrToMail",
                    "Exception: " + e + " message: " + e.getMessage(),
                    HttpStatus. INTERNAL_SERVER_ERROR
            );
        }
    }

    @Transactional
    @Override
    public HouseholdChoresResponseDTO get(String itemId) {
        householdChore obteinedHouseholdChore = itemRepository.getItemById(Long.parseLong(itemId));
        return itemMapper.toItemResponseDTO(obteinedHouseholdChore);
    }
}
