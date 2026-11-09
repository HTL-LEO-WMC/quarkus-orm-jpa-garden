package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Plot extends PanacheEntity {
    private Double width;
    private Double height;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "garden_id")
    private Garden garden;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "plot_id"), inverseJoinColumns = @JoinColumn(name = "vegetable_id"))
    private Set<Vegetable> vegetables = new HashSet<>();

    public void setGarden(Garden garden) {
        if (this.garden != null) {
            this.garden.getPlots().remove(this);
        }
        this.garden = garden;
        if (garden != null) {
            garden.getPlots().add(this);
        }
    }

    public void addVegetable(Vegetable vegetable) {
        vegetable.getPlots().add(this);
        vegetables.add(vegetable);
    }

    public void removeVegetable(Vegetable vegetable) {
        vegetable.getPlots().remove(this);
        vegetables.remove(vegetable);
    }
}
