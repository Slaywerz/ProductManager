package ru.netology.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Smartphone extends Product {
    private String producer;

    public Smartphone(int id, String name, int price, String producer) {
        super(id, name, price);
        this.producer = producer;
    }

    public boolean matches(String search){
        if(super.matches(search)){
            return true;
        }
        return this.getProducer().equalsIgnoreCase(search);
    }
}
