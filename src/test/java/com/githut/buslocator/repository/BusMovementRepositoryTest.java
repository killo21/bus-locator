package com.githut.buslocator.repository;

import com.github.buslocator.model.*;
import com.github.buslocator.repository.BusMovementRepository;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusMovementRepositoryTest extends TestCase{
    private final File file = new File("build/repository-test.json");

    private void cleanup() {
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    public void testShouldSaveBusMovementIntoRepositoryFile() throws IOException{
        cleanup();
        assertFalse(file.exists());
        final BusMovementRepository repository = new BusMovementRepository(file);
        final BusMovement busMovement = createMockBusMovement(1);
        repository.save(busMovement);
        assertTrue(file.exists());
        cleanup();
    }

    @Test
    public void testShouldLoadSavedBusMovement() throws IOException{
        cleanup();
        assertFalse(file.exists());
        final BusMovementRepository repository = new BusMovementRepository(file);
        final BusMovement savedBusMovement = createMockBusMovement(1);
        repository.save(savedBusMovement);

        final BusMovement loadedBusMovcment = repository.load(savedBusMovement.getId());
        assertEquals(loadedBusMovcment.getId(), savedBusMovement.getId());
        assertEquals(loadedBusMovcment.getBus(), savedBusMovement.getBus());
        cleanup();
    }

    @Test
    public void testShouldLoad2SavedBusMovements() throws IOException{
        cleanup();
        assertFalse(file.exists());
        final BusMovementRepository repository = new BusMovementRepository(file);
        final BusMovement savedBusMovement1 = createMockBusMovement(1);
        repository.save(savedBusMovement1);
        final BusMovement savedBusMovement2 = createMockBusMovement(2);
        repository.save(savedBusMovement2);

        final BusMovement loadedBusMovement1 = repository.load(savedBusMovement1.getId());
        assertEquals(loadedBusMovement1.getId(), savedBusMovement1.getId());
        assertEquals(loadedBusMovement1.getBus(), savedBusMovement1.getBus());
        final BusMovement loadedBusMovement2 = repository.load(savedBusMovement2.getId());
        assertEquals(loadedBusMovement2.getId(), savedBusMovement2.getId());
        assertEquals(loadedBusMovement2.getBus(), savedBusMovement2.getBus());

        cleanup();
    }

    private BusMovement createMockBusMovement (long id) {
        final List<PassedStop> passedStops = new ArrayList<>();
        Driver driver = new Driver(1,"Some", "Dude");
        Bus bus = new Bus(1, driver);

        //list of BusStops for Route
        final List<BusStop> busStops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            busStops.add(new BusStop(i, "Stop-" + id + "-" + i));
        }

        for (int i = 0; i < 5; i ++){
            int j = 1;
            passedStops.add(new PassedStop(i, new Date(i), busStops.get(i)));
        }
        final Route route = new Route(1, "TestRoute", busStops);
        return new BusMovement(id, bus, route, passedStops);
    }
}
