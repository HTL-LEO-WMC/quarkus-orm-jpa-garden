package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NamedQuery(
        name = "Garden.sortedByAddress",
        query = "FROM Garden g ORDER BY g.address ASC"
)
public class Garden extends PanacheEntity {
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "garden")
    private Collection<Plot> plots = new ArrayList<>();

    public void addPlot(Plot plot) {
        plot.setGarden(this);
    }

    public void removePlot(Plot plot) {
        plot.setGarden(null);
    }
}
