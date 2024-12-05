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

    public AddressDTO toAddressDTO(Address address){
        return AddressDTO.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .number(address.getNumber())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country(address.getCountry())
                .build();

    }
    public List<PhoneDTO> toPhoneListDTO(List<Phone> phoneDTOS){
        return phoneDTOS.stream().map(this::toPhoneDTO).toList();
    }

    public PhoneDTO toPhoneDTO(Phone phone){
        return PhoneDTO.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .ddi(phone.getDdi())
                .build();
    }
    public User updateUser(UserDTO userDTO, User entity) {
        return User.builder()
                .name(userDTO.getName() != null ? userDTO.getName() : entity.getName())
                .id(entity.getId())
                .password(userDTO.getPassword() != null ? userDTO.getPassword() : entity.getPassword())
                .email(userDTO.getEmail() != null ? userDTO.getEmail() : entity.getEmail())
                .addresses(entity.getAddresses())
                .phones(entity.getPhones())
                .build();


    }

    public Address updateAddress(AddressDTO dto, Address entity){
        return Address.builder()
                .id(entity.getId())
                .addressLine1(dto.getAddressLine1() != null ? dto.getAddressLine1() : entity.getAddressLine1())
                .number(dto.getNumber()  != null ? dto.getNumber() : entity.getNumber())
                .addressLine2(dto.getAddressLine2()  != null ? dto.getAddressLine2() : entity.getAddressLine2())
                .city(dto.getCity()  != null ? dto.getCity() : entity.getCity())
                .state(dto.getState()  != null ? dto.getState() : entity.getState())
                .zip(dto.getZip()  != null ? dto.getZip() : entity.getZip())
                .country(dto.getCountry() != null ? dto.getCountry() : entity.getCountry())
                .build();
    }

    public Phone updatePhone(PhoneDTO dto, Phone entity){
        return Phone.builder()
                .id(entity.getId())
                .number(dto.getNumber() != null ? dto.getNumber() : entity.getNumber())
                .ddi(dto.getDdi() != null ? dto.getDdi() : entity.getDdi())
                .build();

    }
}
