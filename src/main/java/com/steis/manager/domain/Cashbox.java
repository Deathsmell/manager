package com.steis.manager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.repository.EntityGraph;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateEnter;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreate;

    private String address;

    private boolean skno;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("cashboxes")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id")
    @JsonIgnoreProperties("cashboxes")
    private Master master;

}
