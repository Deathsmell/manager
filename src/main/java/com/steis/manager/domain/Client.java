package com.steis.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@EqualsAndHashCode (of = {"id", "name"})
@ToString (of = {"id", "name"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer vat;

    private boolean nonresident;

    private String contract;

    private String mail;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client")
    private Collection<Cashbox> cashboxes = new ArrayList<>();

}
