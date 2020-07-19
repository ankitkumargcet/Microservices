package com.aki.rentcloud.commons.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String firstName;
    String lastName;
    String dlNumber;
    String zipCode;
}
