package edu.pja.mas.s16941.mp5.s16941_mp5.repository;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Repair;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepairRepository extends CrudRepository<Repair, Long> {
    @Query("from Repair as c where c.finishedDate is null")
    public List<Repair> findNotFinishedRepairs();
    @Query("from Repair as d left join fetch d.workers where d.id = :id")
    public Optional<Repair> findById(@Param("id") Long id);
}
