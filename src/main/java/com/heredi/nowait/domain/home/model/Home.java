package com.heredi.nowait.domain.home.model;

import com.heredi.nowait.domain.householdChore.model.householdChore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data // Genera automáticamente getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los atributos
public class Home {

    private Long id;
    private String name;
    private String imageUrl;
    private String phone;
    private String address;
    private String email;
    private String createdAt;
    private List<householdChore> householdChores;
}