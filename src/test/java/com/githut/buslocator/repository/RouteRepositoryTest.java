package com.githut.buslocator.repository;

import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.RouteRepository;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouteRepositoryTest extends TestCase {
    private final File file = new File("build/repository-test.json");

    private void cleanup() {
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    public void testShouldSaveRouteIntoRepositoryFile() throws IOException {
        // given that file does not exist
        cleanup();
        assertFalse(file.exists());
        // when we save a new route
        final RouteRepository repository = new RouteRepository(file);
        final Route route = createMockRoute(1);
        repository.save(route);
        // then file should appear on disk
        assertTrue(file.exists());
        cleanup();
    }

    @Test
    public void testShouldLoadASavedRoute() throws IOException {
        // given that file does not exist
        cleanup();
        assertFalse(file.exists());
        // when we save a new route
        final RouteRepository repository = new RouteRepository(file);
        final Route savedRoute = createMockRoute(1);
        repository.save(savedRoute);

        // then when we can read a route from the repository
        final Route loadedRoute = repository.load(savedRoute.getId());
        assertEquals(loadedRoute.getId(), savedRoute.getId());
        assertEquals(loadedRoute.getName(), savedRoute.getName());
        cleanup();
    }

    @Test
    public void testShouldLoad2SavedRoutes() throws IOException {
        // given that file does not exist
        cleanup();
        assertFalse(file.exists());
        // when we save a new route
        final RouteRepository repository = new RouteRepository(file);
        final Route savedRoute1 = createMockRoute(1);
        repository.save(savedRoute1);
        final Route savedRoute2 = createMockRoute(2);
        repository.save(savedRoute2);

        // then when we can read a route from the repository
        final Route loadedRoute1 = repository.load(savedRoute1.getId());
        assertEquals(loadedRoute1.getId(), savedRoute1.getId());
        assertEquals(loadedRoute1.getName(), savedRoute1.getName());
        final Route loadedRoute2 = repository.load(savedRoute2.getId());
        assertEquals(loadedRoute2.getId(), savedRoute2.getId());
        assertEquals(loadedRoute2.getName(), savedRoute2.getName());

        cleanup();
    }

    private Route createMockRoute(long id) {
        final List<BusStop> busStops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            busStops.add(new BusStop(i, "Stop-" + id + "-" + i));
        }
        return new Route(id, "Test Route", busStops);
    }


}
