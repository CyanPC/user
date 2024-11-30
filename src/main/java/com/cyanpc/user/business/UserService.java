package com.cyanpc.user.business;

import com.cyanpc.user.business.converter.UserConverter;
import com.cyanpc.user.business.dto.UserDTO;
import com.cyanpc.user.infrastructure.entity.User;
import com.cyanpc.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO){
        User user = userConverter.toUser(UserDTO);
        return userConverter.toUserDTO(userRepository.save(user));
    }
}
