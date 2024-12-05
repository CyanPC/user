package com.cyanpc.user.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private String addressLine1;
    private String number;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String country;

}
