package org.acme.entities;

import jakarta.persistence.*;

@Entity
public class Fruit {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String nutritionalValue;
    private String type;

    // Owning-side ... Has ID field.
    @ManyToOne
    @JoinColumn(name = "plotId")
    private Plot plot;

    public Plot getPlot() {
        return plot;
    }

    // Owning-side decouples entities...
    public void setPlot(Plot plot) {
        if (this.plot != null) {
            this.plot.getFruit().remove(this);
        }
        this.plot = plot;
        if (plot != null) {
            plot.getFruit().add(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(String nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
