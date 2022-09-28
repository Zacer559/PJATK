package edu.pja.mas.s16941.mp5.s16941_mp5.test;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Worker;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.WorkerRepository;
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
class WorkerRepositoryTest {

    Worker w1, w2;
    @Autowired
    private WorkerRepository workerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void initData() {
        w1 = Worker.builder()
                .name("Franek")
                .surname("Kimono")
                .employmentDate(LocalDate.parse("2015-04-10"))
                .build();
        workerRepository.save(w1);
        entityManager.flush();
        w2 = Worker.builder()
                .name("Jacek")
                .surname("Stasiak")
                .employmentDate(LocalDate.parse("2018-02-11"))
                .firingDate(LocalDate.parse("2019-02-11"))
                .build();
        workerRepository.save(w2);
        entityManager.flush();
    }


    @Test
    public void testRequiredDependencies() {
        assertNotNull(workerRepository);
    }

    @Test
    public void testFetchWorkers() {
        assertEquals(2, workerRepository.count());
        Iterable<Worker> all = workerRepository.findAll();
        for (Worker a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testSaveWorkers() {
        workerRepository.save(w1);
        entityManager.flush();
        long count = workerRepository.count();
        assertEquals(2, count);
    }

    @Test
    public void testSaveInvalidWorker() {
        assertThrows(ConstraintViolationException.class, () -> {
            w1.setName("");
            workerRepository.save(w1);
            entityManager.flush();
        });

    }

    @Test
    public void testFindFiredWorkers() {
        List<Worker> workers = workerRepository.findFiredWorkers();
        assertEquals(1, workers.size());

    }

    @Test
    public void testFindByEmploymentDateBetween() {
        assertEquals(2, workerRepository.findByEmploymentDateBetween(LocalDate.parse("2015-02-20"), LocalDate.parse("2021-08-20")).size());
    }


}