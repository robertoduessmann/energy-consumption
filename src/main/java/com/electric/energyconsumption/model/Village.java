package com.electric.energyconsumption.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Village {

    @Id
    private Long id;

    private String name;

    public Village() {
    }

    public Village(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Village village = (Village) o;
        return Objects.equals(id, village.id) &&
                Objects.equals(name, village.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
