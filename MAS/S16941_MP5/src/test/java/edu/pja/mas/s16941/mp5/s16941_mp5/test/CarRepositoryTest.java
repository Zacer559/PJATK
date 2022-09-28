package edu.pja.mas.s16941.mp5.s16941_mp5.test;

import edu.pja.mas.s16941.mp5.s16941_mp5.model.Car;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Combustion;
import edu.pja.mas.s16941.mp5.s16941_mp5.model.Electric;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.CarRepository;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.CombustionCarRepository;
import edu.pja.mas.s16941.mp5.s16941_mp5.repository.ElectricCarRepository;
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
class CarRepositoryTest {

    Car c1;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ElectricCarRepository electricCarRepository;
    @Autowired
    private CombustionCarRepository combustionCarRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void initData() {
        c1 = Combustion.builder()
                .mark("Mercedes")
                .model("S500")
                .productionDate(LocalDate.parse("2015-04-10"))
                .serialNumber("WXX74532")
                .fuelConsumptionInLitersPer100km(15)
                .build();
        carRepository.save(c1);
        entityManager.flush();
        c1 = Electric.builder()
                .mark("BMW")
                .model("720d")
                .productionDate(LocalDate.parse("2020-02-20"))
                .serialNumber("WBA723523")
                .batteryCapacityInkWh(50)
                .build();
        carRepository.save(c1);
        entityManager.flush();
    }


    @Test
    public void testRequiredDependencies() {
        assertNotNull(carRepository);
        assertNotNull(electricCarRepository);
        assertNotNull(combustionCarRepository);
    }

    @Test
    public void testFetchCars() {
        assertEquals(2, carRepository.count());
        Iterable<Car> all = carRepository.findAll();
        for (Car a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testSaveCar() {
        carRepository.save(c1);
        entityManager.flush();
        long count = carRepository.count();
        assertEquals(2, count);
    }

    @Test
    public void testSaveInvalidCar() {
        assertThrows(ConstraintViolationException.class, () -> {
            c1.setModel("");
            carRepository.save(c1);
            entityManager.flush();
        });

    }

    @Test
    public void testFindByMark() {
        List<Car> cars = carRepository.findByMark("BMW");
        assertEquals(1, cars.size());
        cars = carRepository.findByMark("Mercedes");
        assertEquals(1, cars.size());
    }

    @Test
    public void testFindProductionDateBetween() {
        assertEquals(2, carRepository.findProductionDateBetween(LocalDate.parse("2015-02-20"), LocalDate.parse("2021-08-20")).size());
    }

    @Test
    public void testCombustionRepository() {
        assertEquals(1, combustionCarRepository.count());
    }

    @Test
    public void testElectricRepository() {
        assertEquals(1, electricCarRepository.count());
    }

}