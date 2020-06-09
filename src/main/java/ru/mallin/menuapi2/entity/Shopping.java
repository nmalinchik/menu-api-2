package ru.mallin.menuapi2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate startPeriod;
    private LocalDate endPeriod;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopping")
    @JsonBackReference
    private Set<Good> goods = new HashSet<>();

}
