package com.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<User> users = new ArrayList<>();

    public Privilege(String name, List<User> users ) {
        this.name = name;
        this.users = users;
    }
}
