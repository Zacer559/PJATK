package edu.pja.mas.s16941.mp5.s16941_mp5.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Electric extends Car {
    @NotNull
    @Min(1)
    int batteryCapacityInkWh;

}
