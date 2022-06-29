package net.catenax.edc.backendservice;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.types.domain.edr.EndpointDataReference;

import static java.lang.String.format;

@Path("/callback")
public class EdcCallbackController {

    private final Monitor monitor;
    private final OkHttpClient httpClient;

    public EdcCallbackController(Monitor monitor, OkHttpClient okHttpClient) {
        this.monitor = monitor;
        this.httpClient = okHttpClient;
    }

    @Path("/endpoint-data-reference")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public void receiveEdcCallback(EndpointDataReference dataReference) {
        monitor.info("Endpoint Data Reference received for agreement: " + dataReference.getProperties().get("cid"));
        monitor.debug("Endpoint: " + dataReference.getEndpoint());
        monitor.debug("AuthKey: " + dataReference.getAuthKey());
        monitor.debug("AuthCode: " + dataReference.getAuthCode());

        retrieveData(dataReference);
    }

    @Path("/store")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public void pushData(String body) {
        monitor.info("New data received: " + body);
    }

    private void retrieveData(EndpointDataReference dataReference) {
        String url = dataReference.getEndpoint();
        monitor.debug("Retrieve data from url  " + url);
        var request = new Request.Builder()
                .url(url)
                .addHeader(dataReference.getAuthKey(), dataReference.getAuthCode())
                .build();

        try (var response = httpClient.newCall(request).execute()) {
            var body = response.body();
            var string = body.string();
            if (response.isSuccessful()) {
                monitor.info("Data plane responded correctly: " + string);
                monitor.info("Received Data: " + string);
            } else {
                monitor.warning(format("Data plane responded with error: %s %s", response.code(), string));
            }
        } catch (Exception e) {
            monitor.severe(format("Error in calling the data plane at %s", url), e);
        }
    }
}
