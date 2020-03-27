package com.steis.manager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode (of = {"id","name"})
@ToString (of = {"id", "name"})
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "masters", fetch = FetchType.LAZY)
    private List<Client> clients;

}

