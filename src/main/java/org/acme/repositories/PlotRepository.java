package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Plot;

import java.util.List;

@ApplicationScoped
public class PlotRepository {

    @Inject
    EntityManager em;

    public List<Plot> findAll() {
        return em.createQuery("select p from Plot p", Plot.class).getResultList();
    }

    public Plot findById(Long id) {
        return em.find(Plot.class, id);
    }

    @Transactional
    public Plot create(Plot plot) {
        em.persist(plot);
        return plot;
    }

    @Transactional
    public Plot update(Long id, Plot changes) {
        Plot plot = em.find(Plot.class, id);
        if (plot == null) {
            return null;
        }
        plot.setWidth(changes.getWidth());
        plot.setHeight(changes.getHeight());
        return plot;
    }

    // Plot is the owning side... removing it removes its rows in plot_vegetable as well.
    @Transactional
    public boolean deleteById(Long id) {
        Plot plot = em.find(Plot.class, id);
        if (plot == null) {
            return false;
        }
        em.remove(plot);
        return true;
    }
}
