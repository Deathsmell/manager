package com.steis.manager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode (of = {"id", "nameModel"})
@ToString(of = {"id","nameModel"})
public class Cashbox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameModel;

    private String serialNumber;

    private Date dateEnter;

    private Date dateCreate;

    private String address;

    private boolean skno;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Master master;

}
