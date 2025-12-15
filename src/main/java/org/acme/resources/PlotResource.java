package org.acme.resources;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import org.acme.entities.Plot;

@ResourceProperties(path = "plots")
public interface PlotResource extends PanacheEntityResource<Plot, Long> {
}