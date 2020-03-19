package com.steis.manager.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
public class Cashbox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String nameModel;

    @Setter
    @Getter
    private String serialNumber;

    @Setter
    @Getter
    private Date dateEnter;

    @Setter
    @Getter
    private Date dateCreate;

    @Setter
    @Getter
    private String address;

    @Setter
    @Getter
    private boolean skno;

    @Setter
    @Getter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Setter
    @Getter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Master master;

}
