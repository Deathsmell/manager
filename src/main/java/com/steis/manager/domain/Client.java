package com.steis.manager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
@EqualsAndHashCode
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer vat;

    @Setter
    @Getter
    private boolean nonresident;

    @Setter
    @Getter
    private String contract;

    @Setter
    @Getter
    private String mail;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable (name = "client_masters",
                joinColumns = @JoinColumn(name = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "master_id"))
    private Collection<Master> masters = new HashSet<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Cashbox> cashboxes = new ArrayList<>();


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vat=" + vat +
                ", nonresident=" + nonresident +
                ", contract='" + contract + '\'' +
                ", mail='" + mail + '\'' +
                ", masters=" + masters +
                ", cashboxes=" + cashboxes +
                "}\n";
    }
}
