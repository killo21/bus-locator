package com.githut.buslocator.repository;

import com.github.buslocator.model.*;
import com.github.buslocator.repository.BusMovementRepository;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BusMovementRepositoryTest {
  private final File file = new File("build/repository-test.json");

  private void cleanup() {
    if (file.exists()) {
      assertTrue(file.delete());
    }
  }

  @Test
  public void testShouldSaveBusMovementIntoRepositoryFile() throws IOException {
    cleanup();
    assertFalse(file.exists());
    final BusMovementRepository repository = new BusMovementRepository(file);
    final BusMovement busMovement = createMockBusMovement(1);
    repository.save(busMovement);
    assertTrue(file.exists());
    cleanup();
  }

  @Test
  public void testShouldLoadSavedBusMovement() throws IOException {
    cleanup();
    assertFalse(file.exists());
    final BusMovementRepository repository = new BusMovementRepository(file);
    final BusMovement savedBusMovement = createMockBusMovement(1);
    repository.save(savedBusMovement);

    final BusMovement loadedBusMovcment = repository.load(savedBusMovement.getId());
    Assert.assertEquals(loadedBusMovcment.getId(), savedBusMovement.getId());
    cleanup();
  }

  @Test
  public void testShouldLoad2SavedBusMovements() throws IOException {
    cleanup();
    assertFalse(file.exists());
    final BusMovementRepository repository = new BusMovementRepository(file);
    final BusMovement savedBusMovement1 = createMockBusMovement(1);
    repository.save(savedBusMovement1);
    final BusMovement savedBusMovement2 = createMockBusMovement(2);
    repository.save(savedBusMovement2);

    final BusMovement loadedBusMovement1 = repository.load(savedBusMovement1.getId());
    Assert.assertEquals(loadedBusMovement1.getId(), savedBusMovement1.getId());
    final BusMovement loadedBusMovement2 = repository.load(savedBusMovement2.getId());
    Assert.assertEquals(loadedBusMovement2.getId(), savedBusMovement2.getId());

    cleanup();
  }

  @Test
  public void testShouldRemoveBusMovementBySpecifiedId() throws Exception {
    cleanup();
    assertFalse(file.exists());

    {
      final BusMovementRepository repository = new BusMovementRepository(file);
      Assert.assertEquals(0, repository.loadAll().size());
      final BusMovement savedBusMovement1 = createMockBusMovement(1);
      repository.save(savedBusMovement1);
      Assert.assertEquals(1, repository.loadAll().size());
      repository.remove(1);
    }

    {
      final BusMovementRepository repository = new BusMovementRepository(file);
      Assert.assertEquals(0, repository.loadAll().size());
    }
  }

  private BusMovement createMockBusMovement(long id) {
    final List<PassedStop> passedStops = new ArrayList<>();

    //list of BusStops for Route
    final List<BusStop> busStops = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      busStops.add(new BusStop(i, "Stop-" + id + "-" + i));
    }

    for (int i = 0; i < 5; i++) {
      passedStops.add(new PassedStop(i, LocalDateTime.now(), busStops.get(i)));
    }
    final Route route = new Route(1, "TestRoute", busStops);
    return new BusMovement(id, route, passedStops);
  }
}
