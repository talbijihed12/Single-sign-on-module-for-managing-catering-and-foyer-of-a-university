package com.example.restorationmicroservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table( name = "Meal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Meal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = " Name cannot be empty or Null")
    @NotEmpty
    private String name;
    private Integer voteCount = 0;
    @Enumerated(EnumType.STRING)
    private TypeCategory typeCategory;
    //date ou le menu doit servi
    @Temporal(TemporalType.DATE)
    private Date datestart;
    private String upload;
    @ManyToOne
    private Menu menu;
}

