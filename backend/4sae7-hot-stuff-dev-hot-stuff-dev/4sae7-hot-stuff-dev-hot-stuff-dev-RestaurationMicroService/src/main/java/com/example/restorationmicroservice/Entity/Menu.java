package com.example.restorationmicroservice.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table( name = "Menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = " Name cannot be empty or Null")
    @NotEmpty
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeEmplacement typeEmplacement;
    @Enumerated(EnumType.STRING)
    private TypeMenu typeMenu;
    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    Set<Meal> meals=new HashSet<>();

}