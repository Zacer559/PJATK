package edu.pja.mas.s16941.mp5.s16941_mp5.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    double price;

    @NotNull
    private LocalDate startDate;
    private LocalDate finishedDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;

    @ManyToMany(cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Worker> workers= new HashSet<>();
}
