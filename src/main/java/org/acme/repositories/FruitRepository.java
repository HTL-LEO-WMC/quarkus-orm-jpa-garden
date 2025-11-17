package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Fruit;

import java.util.List;

@ApplicationScoped
public class FruitRepository {
    @Inject
    EntityManager em;
    
    @Transactional
    public List<Fruit> findAll() {
        return em.createQuery("select f from Fruit f", Fruit.class).getResultList();
    }
    
    @Transactional
    public Fruit findById(Long id) {
        return em.find(Fruit.class, id);
    }
    
    @Transactional
    public Fruit save(Fruit fruit) {
        return em.merge(fruit);
    }
    
    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from Fruit where id=:id", Fruit.class).setParameter("id", id).getSingleResult();
    }
}
