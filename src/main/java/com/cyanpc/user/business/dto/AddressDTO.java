package com.cyanpc.user.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private String addressLine1;
    private String number;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String country;

}
