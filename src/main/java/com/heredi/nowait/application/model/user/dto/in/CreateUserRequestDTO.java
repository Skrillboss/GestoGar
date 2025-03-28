package com.heredi.nowait.application.model.user.dto.in;

import com.heredi.nowait.application.model.home.dto.in.HomeRequestDTO;
import com.heredi.nowait.application.model.paymentInfo.dto.in.PaymentInfoRequestDTO;
import com.heredi.nowait.application.model.role.dto.RoleDTO;
import com.heredi.nowait.application.model.shift.dto.in.ShiftRequestDTO;
import lombok.Data;
import java.util.List;

@Data
public class CreateUserRequestDTO {

    private String name;
    private String nickName;
    private String email;
    private String password;
    private String phoneNumber;
    private RoleDTO roleRequestDTO;
    private List<PaymentInfoRequestDTO> paymentInfoRequestDTOList;
    private HomeRequestDTO homeRequestDTO;
    private List<ShiftRequestDTO> shifts;
}
