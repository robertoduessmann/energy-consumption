package com.electric.energyconsumption.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class EnergyConsumption {

    @Id
    @GeneratedValue
    private Long id;

    @Column(precision = 11, scale = 3)
    private BigDecimal consumption;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn(name = "village_id")
    private Village village;

    public EnergyConsumption(){
    }

    public EnergyConsumption(BigDecimal consumption, Village village) {
        this.consumption = consumption;
        this.village = village;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public Date getDate() {
        return date;
    }

    public Village getVillage() {
        return village;
    }
}
