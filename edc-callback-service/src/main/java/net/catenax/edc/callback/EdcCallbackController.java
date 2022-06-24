package net.catenax.edc.callback;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
public class EdcCallbackController {

    @PostMapping("/endpoint-data-reference")
    public void receiveEdcCallback(@RequestBody EndpointDataReference dataReference) {
        System.out.println("Endpoint Data Reference received for agreement: " + dataReference.getProperties().get("cid"));
        System.out.println("Endpoint: " + dataReference.getEndpoint());
        System.out.println("AuthKey: " + dataReference.getAuthKey());
        System.out.println("AuthCode: " + dataReference.getAuthCode());
    }
}
