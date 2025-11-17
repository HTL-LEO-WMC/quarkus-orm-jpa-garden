package org.acme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.Set;

@Entity
public class Plot {
    @Id
    @GeneratedValue
    private Long id;

    private Double width;
    private Double height;

    @OneToMany(mappedBy = "plot")
    private Set<Fruit> fruit;

    public void addFruit(Fruit fruit) {
        fruit.setPlot(this);
    }

    public void removeFruit(Fruit fruit) {
        fruit.setPlot(null);
    }

    public Collection<Fruit> getFruit() {
        return fruit;
    }

    public void setFruit(Set<Fruit> fruit) {
        this.fruit = fruit;
    }

    @OneToMany(mappedBy = "plot")
    private Collection<Vegetable> vegetables;

    public Collection<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(Collection<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
