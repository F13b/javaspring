package ru.spring.javaspring.model;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.javaspring.repo.VegetablesRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vegetables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name, color, sun, fertilizer;
    Integer waterTemperature;

    public Vegetables(String name, String color, String sun, String fertilizer, Integer waterTemperature) {
        this.name = name;
        this.color = color;
        this.sun = sun;
        this.fertilizer = fertilizer;
        this.waterTemperature = waterTemperature;
    }

    public Vegetables() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public Integer getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Integer waterTemperature) {
        this.waterTemperature = waterTemperature;
    }
}
