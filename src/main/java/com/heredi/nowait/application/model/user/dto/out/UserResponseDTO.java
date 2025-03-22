package com.heredi.nowait.application.model.user.dto.out;

import com.heredi.nowait.application.model.home.dto.out.HomeResponseDTO;
import com.heredi.nowait.application.model.paymentInfo.dto.out.PaymentInfoResponseDTO;
import com.heredi.nowait.application.model.role.dto.RoleDTO;
import com.heredi.nowait.application.model.shift.dto.out.ShiftResponseDTO;
import lombok.Data;
import java.util.List;

@Data
public class UserResponseDTO {

    private String name;
    private String nickName;
    private String email;
    private String phoneNumber;
    private RoleDTO roleDTO;
    private List<PaymentInfoResponseDTO> paymentInfoResponseDTOList;
    private HomeResponseDTO homeResponseDTO;
    private List<ShiftResponseDTO> shifts;
}
