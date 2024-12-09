package com.cyanpc.user.controller;

import com.cyanpc.user.business.UserService;
import com.cyanpc.user.business.dto.AddressDTO;
import com.cyanpc.user.business.dto.PhoneDTO;
import com.cyanpc.user.business.dto.UserDTO;
import com.cyanpc.user.infrastructure.entity.Address;
import com.cyanpc.user.infrastructure.entity.User;
import com.cyanpc.user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.EndElement;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }
    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                        userDTO.getPassword())
        ); //sad face
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UserDTO> findUserByEmail(@RequestParam("email")String email){
        return  ok(userService.findUserByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("User executed.");
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateUserData(token, dto));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO dto, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.updateAddress(id, dto));
    }
    @PutMapping("/phone")
    public ResponseEntity<PhoneDTO> updateAddress(@RequestBody PhoneDTO dto, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.updatePhone(id, dto));
    }
    @PostMapping("/address")
    public ResponseEntity<AddressDTO> insertAddress(@RequestBody AddressDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.insertAddress(token, dto));
    }
    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> insertPhone(@RequestBody PhoneDTO dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.insertPhone(token, dto));
    }
}

