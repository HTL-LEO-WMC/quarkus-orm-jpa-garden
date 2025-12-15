package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Vegetable  extends PanacheEntity {
    private String name;
    private Integer nutritionalValue;

    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @ManyToMany(mappedBy = "vegetables")
    private Set<Plot> plots;

    // Convenience-method 1.
    public void addPlot(Plot plot) {
        plot.addVegetable(this);
    }

    // Convenience-method 2.
    public void removePlot(Plot plot) {
        plot.removeVegetable(this);
    }
}
