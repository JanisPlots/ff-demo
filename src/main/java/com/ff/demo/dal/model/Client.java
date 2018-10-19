package com.ff.demo.dal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "client")
public class Client extends AuditModel {

    @Id
    @GeneratedValue(generator = "client_generator")
    @SequenceGenerator(
            name = "client_generator",
            sequenceName = "client_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Column(columnDefinition = "varchar(39)")
    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
