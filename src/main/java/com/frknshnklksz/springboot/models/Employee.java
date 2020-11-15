package com.frknshnklksz.springboot.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "AGE")
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "BIRTH_DAY")
    private LocalDate birthday;

    @Column(name = "E_MAIL")
    private String email;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "IDENTITY_NUMBER", length = 11, unique = true, nullable = false)
    private String identityNumber;

    @Column(name = "DATE_OF_CREATE")
    private String dateOfCreate;

    @Column(name = "DATE_OF_UPDATE")
    private String dateOfUpdate;

    @Column(name = "GENDER", length = 1)
    private String gender;


}
