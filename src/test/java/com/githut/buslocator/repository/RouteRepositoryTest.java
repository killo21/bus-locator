package com.githut.buslocator.repository;

import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.RouteRepository;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteRepositoryTest extends TestCase {
    private final File file = new File("build/repository-test.json");

    private void cleanup() {
        if (file.exists()) {            assertTrue(file.delete());
        }
    }

    public void testShouldSaveRouteIntoRepositoryFile() throws IOException {
        // given that file does not exist
        cleanup();
        assertFalse(file.exists());
        // when we save a new route
        final RouteRepository repository = new RouteRepository(file);
        final Route route = createMockRoute();
        repository.save(route);
        // then file should appear on disk
        assertTrue(file.exists());
        cleanup();
    }

    public void testShouldLoadASavedRoute() throws IOException {
        // given that file does not exist
        cleanup();
        assertFalse(file.exists());
        // when we save a new route
        final RouteRepository repository = new RouteRepository(file);
        final Route savedRoute = createMockRoute();
        repository.save(savedRoute);

        // then when we can read a route from the repository
        final Route loadedRoute = repository.load(savedRoute.getId());
        assertEquals(loadedRoute.getId(), savedRoute.getId());
        assertEquals(loadedRoute.getName(), savedRoute.getName());
        cleanup();
    }

    private Route createMockRoute() {
        final List<BusStop> busStops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            busStops.add(new BusStop(i, "Stop " + i));
        }
        return new Route(1, "Test Route", busStops);
    }

}
