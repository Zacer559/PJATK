package edu.pja.mas.s16941.mp5.s16941_mp5.repository;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    public List<Car> findByMark(String mark);

    public List<Car> findByModel(String model);

    public List<Car> findBySerialNumber(String serialNumber);


    @Query("from Car as c where c.productionDate between :minDate and :maxDate")
    public List<Car> findProductionDateBetween(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);

    @Query("from Car as d left join fetch d.repairs where d.id = :id")
    public Optional<Car> findById(@Param("id") Long id);
}
