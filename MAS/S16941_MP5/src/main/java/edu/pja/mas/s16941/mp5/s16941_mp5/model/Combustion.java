package edu.pja.mas.s16941.mp5.s16941_mp5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Combustion extends Car {
    @NotNull
    @Min(1)
    double fuelConsumptionInLitersPer100km;

    @Size(min = 1, max = 2)
    String euroNorm;
}
