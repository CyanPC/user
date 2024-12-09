package com.cyanpc.user.business;

import com.cyanpc.user.business.converter.UserConverter;
import com.cyanpc.user.business.dto.AddressDTO;
import com.cyanpc.user.business.dto.PhoneDTO;
import com.cyanpc.user.business.dto.UserDTO;
import com.cyanpc.user.infrastructure.entity.Address;
import com.cyanpc.user.infrastructure.entity.Phone;
import com.cyanpc.user.infrastructure.entity.User;
import com.cyanpc.user.infrastructure.exceptions.ConflictException;
import com.cyanpc.user.infrastructure.exceptions.ResourceNotFoundException;
import com.cyanpc.user.infrastructure.repository.AddressRepository;
import com.cyanpc.user.infrastructure.repository.PhoneRepository;
import com.cyanpc.user.infrastructure.repository.UserRepository;
import com.cyanpc.user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;


    public UserDTO saveUser(UserDTO userDTO){
        emailExists(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.toUser(userDTO);
        return userConverter.toUserDTO(userRepository.save(user));
    }
    public void emailExists(String email){
        try{
            boolean exists = verifyExistingEmail(email);
            if(exists){
                throw new ConflictException("Email " + email + " already registered!");
            }
        }catch (ConflictException e){
            throw new ConflictException("Email already registered" + e.getCause());
        }
    }

    public boolean verifyExistingEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public UserDTO findUserByEmail(String email){
        try {
        return userConverter.toUserDTO(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not found" + email)));
    }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Email not found" + email);
        }
    }


    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserDTO updateUserData(String token,UserDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()):null);
        User userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email not found"));

        User user = userConverter.updateUser(dto , userEntity);

        return userConverter.toUserDTO(userRepository.save(user));

    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO){

        Address entity = addressRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id not found" + id));

        Address address = userConverter.updateAddress(addressDTO, entity);

        return userConverter.toAddressDTO(addressRepository.save(address));

    }
    public PhoneDTO updatePhone(Long id, PhoneDTO phoneDTO){

        Phone entity = phoneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id not found" + id));

        Phone phone = userConverter.updatePhone(phoneDTO, entity);

        return userConverter.toPhoneDTO(phoneRepository.save(phone));
    }

    public AddressDTO insertAddress(String token, AddressDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email not found " + email));

        Address address = userConverter.toAddressEntity(dto, user.getId());
        Address addressEntity = addressRepository.save(address);
        return userConverter.toAddressDTO(addressEntity);
    }

    public PhoneDTO insertPhone(String token, PhoneDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email not found " + email));

        Phone phone = userConverter.toPhoneEntity(dto, user.getId());
        Phone phoneEntity = phoneRepository.save(phone);
        return userConverter.toPhoneDTO(phoneRepository.save(phone));
    }
}
