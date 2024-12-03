package com.cyanpc.user.business;

import com.cyanpc.user.business.converter.UserConverter;
import com.cyanpc.user.business.dto.UserDTO;
import com.cyanpc.user.infrastructure.entity.User;
import com.cyanpc.user.infrastructure.exceptions.ConflictException;
import com.cyanpc.user.infrastructure.exceptions.ResourceNotFoundException;
import com.cyanpc.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

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
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not found" + email));
    }
    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }
}
