package net.catenax.edc.backendservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;

import java.util.concurrent.atomic.AtomicReference;

@Path("/backend")
public class ConsumerBackendServiceController {

    private final Monitor monitor;
    private final AtomicReference<String> data = new AtomicReference<>();

    public ConsumerBackendServiceController(Monitor monitor) {
        this.monitor = monitor;
    }

    @Path("/store")
    @POST
    public void pushData(String body) {
        monitor.info("New data received: " + body);
        data.set(body);
    }

    @Path("/data")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData() {
        monitor.info("Retrieve data...");
        return data.get();
    }



}
