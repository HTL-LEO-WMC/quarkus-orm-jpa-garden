package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Plot;
import org.acme.entities.Vegetable;

import java.util.List;

@ApplicationScoped
public class VegetableRepository {

    @Inject
    EntityManager em;

    public List<Vegetable> findAll() {
        return em.createQuery("select v from Vegetable v", Vegetable.class).getResultList();
    }

    public Vegetable findById(Long id) {
        return em.find(Vegetable.class, id);
    }

    @Transactional
    public Vegetable create(Vegetable vegetable) {
        em.persist(vegetable);
        return vegetable;
    }

    @Transactional
    public Vegetable update(Long id, Vegetable changes) {
        Vegetable vegetable = em.find(Vegetable.class, id);
        if (vegetable == null) {
            return null;
        }
        vegetable.setName(changes.getName());
        vegetable.setNutritionalValue(changes.getNutritionalValue());
        return vegetable;
    }

    @Transactional
    public boolean deleteById(Long id) {
        Vegetable vegetable = em.find(Vegetable.class, id);
        if (vegetable == null) {
            return false;
        }
        // Vegetable is the inverse side... unlink it from the owning plots first.
        for (Plot plot : List.copyOf(vegetable.getPlots())) {
            vegetable.removePlot(plot);
        }
        em.remove(vegetable);
        return true;
    }
}
