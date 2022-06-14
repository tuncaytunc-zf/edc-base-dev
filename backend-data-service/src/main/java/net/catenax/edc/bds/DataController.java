package net.catenax.edc.bds;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/data")
public class DataController {

    Map<String, String> asset = new HashMap<>();

    @GetMapping("/{id}")
    public String get(@PathVariable("id") String assetId) {
        if (!asset.containsKey(assetId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The asset id was not found in database");
        }

        System.out.println("Returning data for contract offer " + assetId);

        return asset.get(assetId);
    }

    @PostMapping("/{id}")
    public void store(@PathVariable("id") String assetId, @RequestBody String data) {
        System.out.println("Saving data for asset " + assetId);

        asset.put(assetId, data);
    }
}
