package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Plot  extends PanacheEntity {
    private Double width;
    private Double height;

    // Owning-side... Holds garden_id.
    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @ManyToOne
    @JoinColumn(name = "garden_id")
    private Garden garden;

    // Owning-side... set other side as well.
    public void setGarden(Garden garden) {
        if (this.garden != null) {
            this.garden.getPlots().remove(this);
        }
        this.garden = garden;
        if (garden != null) {
            garden.getPlots().add(this);
        }
    }

    // Owning-side... Holds JoinTable settings.
    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "plot_id"), inverseJoinColumns = @JoinColumn(name = "vegetable_id"))
    private Set<Vegetable> vegetables;

    // Owning-side... set other side as well.
    public void addVegetable(Vegetable vegetable) {
        vegetable.getPlots().add(this);
        vegetables.add(vegetable);
    }

    // Owning-side... set other side as well.
    public void removeVegetable(Vegetable vegetable) {
        vegetable.getPlots().remove(this);
        vegetables.remove(vegetable);
    }
}
