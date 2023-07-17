package com.example.foyermicroservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class Block implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idB ;

    @Enumerated(EnumType.STRING)
    Sexe sexe;
    @Enumerated(EnumType.STRING)
    Types type ;
    @JsonIgnore
    @OneToMany(mappedBy = "block")
    Set<Room> rooms = new HashSet<>();
}
