package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Garden;
import org.acme.entities.Plot;

import java.util.List;

@ApplicationScoped
public class GardenRepository {

    @Inject
    EntityManager em;

    public List<Garden> findAll() {
        return em.createQuery("select g from Garden g", Garden.class).getResultList();
    }

    public Garden findById(Long id) {
        return em.find(Garden.class, id);
    }

    @Transactional
    public Garden create(Garden garden) {
        em.persist(garden);
        return garden;
    }

    // Change the managed entity instead of merging the one from the request...
    // the request has no plots (@JsonIgnore), merge would cut the relation.
    @Transactional
    public Garden update(Long id, Garden changes) {
        Garden garden = em.find(Garden.class, id);
        if (garden == null) {
            return null;
        }
        garden.setAddress(changes.getAddress());
        return garden;
    }

    @Transactional
    public boolean deleteById(Long id) {
        Garden garden = em.find(Garden.class, id);
        if (garden == null) {
            return false;
        }
        // Plots hold the foreign key... release them first.
        for (Plot plot : List.copyOf(garden.getPlots())) {
            garden.removePlot(plot);
        }
        em.remove(garden);
        return true;
    }
}
