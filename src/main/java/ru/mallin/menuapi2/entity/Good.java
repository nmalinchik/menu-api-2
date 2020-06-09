package ru.mallin.menuapi2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String type;
    private String measure;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "shopping_id")
    @JsonBackReference
    private Shopping shopping;

    public Good(String title, String type, String measure, double amount, Shopping shopping) {
        this.title = title;
        this.type = type;
        this.measure = measure;
        this.amount = amount;
        this.shopping = shopping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id &&
                Double.compare(good.amount, amount) == 0 &&
                title.equals(good.title) &&
                type.equals(good.type) &&
                measure.equals(good.measure) &&
                shopping.equals(good.shopping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, measure, amount, shopping);
    }
}
