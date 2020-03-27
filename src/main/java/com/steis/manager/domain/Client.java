package com.steis.manager.domain;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable (name = "client_masters",
                joinColumns = @JoinColumn(name = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "master_id"))
    private Collection<Master> masters = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Cashbox> cashboxes = new ArrayList<>();

}
