package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Vegetable extends PanacheEntity {
    private String name;
    private Integer nutritionalValue;

    @JsonIgnore
    @ManyToMany(mappedBy = "vegetables")
    private Set<Plot> plots = new HashSet<>();

    public void addPlot(Plot plot) {
        plot.addVegetable(this);
    }

    public void removePlot(Plot plot) {
        plot.removeVegetable(this);
    }
}
