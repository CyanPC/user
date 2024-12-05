package com.cyanpc.user.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTO {

    private Long id;
    private String number;
    private String ddi;

}
