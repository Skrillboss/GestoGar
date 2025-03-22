package com.heredi.nowait.application.model.user.mapper;

import com.heredi.nowait.application.model.home.mapper.HomeMapper;
import com.heredi.nowait.application.model.paymentInfo.mapper.PaymentInfoMapper;
import com.heredi.nowait.application.model.role.dto.mapper.RoleMapper;
import com.heredi.nowait.application.model.shift.mapper.ShiftMapper;
import com.heredi.nowait.application.model.user.dto.in.CreateUserRequestDTO;
import com.heredi.nowait.application.model.user.dto.out.UserResponseDTO;
import com.heredi.nowait.domain.user.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;
    private final PaymentInfoMapper paymentInfoMapper;
    private final HomeMapper homeMapper;
    private final ShiftMapper shiftMapper;

    public UserMapper(RoleMapper roleMapper, PaymentInfoMapper paymentInfoMapper, HomeMapper homeMapper, ShiftMapper shiftMapper) {
        this.roleMapper = roleMapper;
        this.paymentInfoMapper = paymentInfoMapper;
        this.homeMapper = homeMapper;
        this.shiftMapper = shiftMapper;
    }

    public UserResponseDTO toUserResponseDTO(Users user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setName(user.getName());
        dto.setNickName(user.getNickName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRoleDTO(roleMapper.toRoleDTO(user.getAuthority()));
        dto.setPaymentInfoResponseDTOList(paymentInfoMapper.toPaymentInfoListResponseDTO(user.getPaymentInfoList()));
        dto.setHomeResponseDTO(homeMapper.toHomeDTO(user.getHome()));

        return dto;
    }

    public Users toUser(CreateUserRequestDTO createUserRequestDTO) {
        if (createUserRequestDTO == null) {
            return null;
        }

        Users user = new Users();
        user.setName(createUserRequestDTO.getName());
        user.setNickName(createUserRequestDTO.getNickName());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setPassword(createUserRequestDTO.getPassword());
        user.setPhoneNumber(createUserRequestDTO.getPhoneNumber());
        user.setAuthority(roleMapper.toRole(createUserRequestDTO.getRoleRequestDTO()));
        user.setPaymentInfoList(paymentInfoMapper.toPaymentInfoList(createUserRequestDTO.getPaymentInfoRequestDTOList()));
        user.setHome(homeMapper.toHome(createUserRequestDTO.getHomeRequestDTO()));
        user.setShifts(shiftMapper.toShiftList(createUserRequestDTO.getShifts()));

        return user;
    }
}
