package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Garden extends PanacheEntity {
    private String address;
    
    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @OneToMany(mappedBy = "garden")
    private Collection<Plot> plots;

    // Convenience-method 1.
    public void addPlot(Plot plot) {
        plot.setGarden(this);
    }

    // Convenience-method 2.
    public void removePlot(Plot plot) {
        plot.setGarden(null);
    }
}
