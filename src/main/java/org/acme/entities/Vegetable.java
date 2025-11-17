package org.acme.entities;

import jakarta.persistence.*;

@Entity
public class Vegetable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer nutritionalValue;

    // Owning-side ... holds plot-id.
    @ManyToOne
    @JoinColumn(name = "plotId")
    private Plot plot;

    public Plot getPlot() {
        return plot;
    }

    // Owning-side... remove from plot as well.
    public void setPlot(Plot plot) {
        if(this.plot != null) {
            this.plot.getVegetables().remove(this);
        }
        this.plot = plot;
        if(plot != null) {
            plot.getVegetables().add(this);
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

    public Integer getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(Integer nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }
}
