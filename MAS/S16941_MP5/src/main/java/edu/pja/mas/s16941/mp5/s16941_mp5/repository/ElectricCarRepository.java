package edu.pja.mas.s16941.mp5.s16941_mp5.repository;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Car;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Electric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ElectricCarRepository extends CrudRepository<Electric, Long> {

}
