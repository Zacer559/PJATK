package edu.pja.mas.s16941.mp5.s16941_mp5.repository;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Car;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    public List<Worker> findByName(String mark);

    public List<Worker> findBySurname(String model);

    public List<Worker> findByEmploymentDateBetween(LocalDate start, LocalDate end);

    @Query("from Worker as c where c.firingDate is not null")
    public List<Worker> findFiredWorkers();
    @Query("from Worker as d left join fetch d.repairs where d.id = :id")
    public Optional<Worker> findById(@Param("id") Long id);
}

