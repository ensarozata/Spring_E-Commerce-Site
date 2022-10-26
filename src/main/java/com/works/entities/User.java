package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Adınızı Giriniz.")
    private String firstName;
    @NotBlank(message = "Soyadınızı Giriniz.")
    private String lastName;
    @NotBlank(message = "Email giriniz.")
    @Email(message = "Email formatında giriniz.")
    private String email;

    @NotBlank(message = "Şifrenizi Giriniz.")
    @Pattern(message = "Password must contain min one upper,lower letter and 0-9 digit number ", regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})")
    private String password;

    private boolean enabled;
    private boolean tokenExpired;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade={CascadeType.PERSIST, CascadeType.DETACH})
    private List<Orders> orders;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}
