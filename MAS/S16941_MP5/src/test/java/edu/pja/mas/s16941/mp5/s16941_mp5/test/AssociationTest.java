package edu.pja.mas.s16941.mp5.s16941_mp5.test;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Electric;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Repair;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Worker;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.ElectricCarRepository;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.RepairRepository;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AssociationTest {
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private ElectricCarRepository carRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    Electric c1;
    Repair r1;
    Worker w1;
    @Test
    public void testRequiredDependencies(){
        assertNotNull(carRepository);
        assertNotNull(repairRepository);
        assertNotNull(workerRepository);
    }
    @BeforeEach
    public void initData() {
        c1 = Electric.builder()
                .mark("BMW")
                .model("720d")
                .productionDate(LocalDate.parse("2020-02-20"))
                .serialNumber("WBA723523")
                .batteryCapacityInkWh(50)
                .build();
        r1 = Repair.builder()
                .startDate(LocalDate.parse("2018-02-11"))
                .price(500.0)
                .build();
        w1 = Worker.builder()
                .name("Franek")
                .surname("Kimono")
                .employmentDate(LocalDate.parse("2015-04-10"))
                .build();
    }
    @Test
    public void testSave() throws InterruptedException {
        c1.getRepairs().add(r1);
        carRepository.save(c1);
        r1.setCar(c1);
        w1.getRepairs().add(r1);
        workerRepository.save(w1);
        r1.getWorkers().add(w1);
        repairRepository.save(r1);
        entityManager.flush();
        Optional<Electric> z = carRepository.findById(1L);
        z.ifPresent(electric -> System.out.println(electric.getMark()));
        z.ifPresent(electric -> assertEquals("BMW",electric.getMark()));
        Optional<Repair> w = repairRepository.findById(1L);
        if(w.isPresent()){
            System.out.println(w.get());
            System.out.println(w.get().getId());
        }



    }


}
