package com.steis.manager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
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
    private Collection<Master> masters;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Collection<Cashbox> cashboxes;
}
