package com.trips.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.trips.entity.Shopping;
import com.trips.service.ShoppingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShoppingServiceImpl implements ShoppingService {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Shopping> retrieveAvailableShoppings() {
        List<Shopping> shoppings = new ArrayList<>();
        try {
            Files.walk(Paths.get("trips/shoppings/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(file -> {
                        try {
                            shoppings.add(mapper.readValue(file, Shopping.class));
                        } catch (IOException e) {
                            log.error("Cannot read from file. " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            log.error("Cannot find the specified folder" + e.getMessage());
        }
        return shoppings;
    }
}
