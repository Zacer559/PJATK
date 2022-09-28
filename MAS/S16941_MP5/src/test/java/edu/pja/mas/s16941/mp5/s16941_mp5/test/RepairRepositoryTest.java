package edu.pja.mas.s16941.mp5.s16941_mp5.test;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Repair;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.RepairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepairRepositoryTest {
    Repair r1, r2;
    @Autowired
    private RepairRepository repairRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void initData() {
        r1 = Repair.builder()
                .startDate(LocalDate.parse("2018-02-11"))
                .price(500.0)
                .build();
        repairRepository.save(r1);
        entityManager.flush();
        r2 = Repair.builder()
                .startDate(LocalDate.parse("2020-02-11"))
                .finishedDate(LocalDate.parse("2020-02-13"))
                .price(900.0)
                .build();
        repairRepository.save(r2);
        entityManager.flush();
    }


    @Test
    public void testRequiredDependencies() {
        assertNotNull(repairRepository);
    }

    @Test
    public void testFetchRepairs() {
        assertEquals(2, repairRepository.count());
        Iterable<Repair> all = repairRepository.findAll();
        for (Repair a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testSaveWorkers() {
        repairRepository.save(r1);
        entityManager.flush();
        long count = repairRepository.count();
        assertEquals(2, count);
    }

    @Test
    public void testSaveInvalidWorker() {
        assertThrows(ConstraintViolationException.class, () -> {
            r1.setPrice(-5.0);
            repairRepository.save(r1);
            entityManager.flush();
        });

    }

    @Test
    public void testFindNotFinishedRepairs() {
        List<Repair> repairs = repairRepository.findNotFinishedRepairs();
        assertEquals(1, repairs.size());

    }


}