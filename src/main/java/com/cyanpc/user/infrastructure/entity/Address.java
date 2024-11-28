package com.cyanpc.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "addressLine1", length = 200)
    private String addressLine1;
    @Column(name = "number", length = 5)
    private String number;
    @Column(name = "addressLine2", length = 100)
    private String addressLine2;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "zip", length = 9)
    private String zip;
    @Column(name = "country")
    private String country;
}
