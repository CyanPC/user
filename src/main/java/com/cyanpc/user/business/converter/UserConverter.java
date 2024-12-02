package com.cyanpc.user.business.converter;

import com.cyanpc.user.business.dto.AddressDTO;
import com.cyanpc.user.business.dto.PhoneDTO;
import com.cyanpc.user.business.dto.UserDTO;
import com.cyanpc.user.infrastructure.entity.Address;
import com.cyanpc.user.infrastructure.entity.Phone;
import com.cyanpc.user.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    public User toUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(toAddressList(userDTO.getAddresses()))
                .phones(toPhoneList(userDTO.getPhones()))
                .build();

    }
    public List<Address> toAddressList(List<AddressDTO> addressDTOS){
        return addressDTOS.stream().map(this::toAddress).toList();
    }

    public Address toAddress(AddressDTO addressDTO){
        return Address.builder()
                .addressLine1(addressDTO.getAddressLine1())
                .number(addressDTO.getNumber())
                .addressLine2(addressDTO.getAddressLine2())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .zip(addressDTO.getZip())
                .country(addressDTO.getCountry())
                .build();

    }
    public List<Phone> toPhoneList(List<PhoneDTO> phoneDTOS){
        return phoneDTOS.stream().map(this::toPhone).toList();
    }

    public Phone toPhone(PhoneDTO phoneDTO){
        return Phone.builder()
                .number(phoneDTO.getNumber())
                .ddi(phoneDTO.getDdi())
                .build();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(toAddressListDTO(user.getAddresses()))
                .phones(toPhoneListDTO(user.getPhones()))
                .build();

    }
    public List<AddressDTO> toAddressListDTO(List<Address> addressDTOS){
        return addressDTOS.stream().map(this::toAddressDTO).toList();
    }

    public AddressDTO toAddressDTO(Address addressDTO){
        return AddressDTO.builder()
                .addressLine1(addressDTO.getAddressLine1())
                .number(addressDTO.getNumber())
                .addressLine2(addressDTO.getAddressLine2())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .zip(addressDTO.getZip())
                .country(addressDTO.getCountry())
                .build();

    }
    public List<PhoneDTO> toPhoneListDTO(List<Phone> phoneDTOS){
        return phoneDTOS.stream().map(this::toPhoneDTO).toList();
    }

    public PhoneDTO toPhoneDTO(Phone phoneDTO){
        return PhoneDTO.builder()
                .number(phoneDTO.getNumber())
                .ddi(phoneDTO.getDdi())
                .build();
    }
}
