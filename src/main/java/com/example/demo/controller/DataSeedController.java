package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DataSeedService;

@RestController
@RequestMapping("/seed")
@Tag(name = "Data Seeding", description = "Endpoints for seeding sample data")
public class DataSeedController {

    private final DataSeedService dataSeedService;

    public DataSeedController(DataSeedService dataSeedService) {
        this.dataSeedService = dataSeedService;
    }

    @Operation(summary = "Seed sample data for all entities")
    @PostMapping
    public String seedData() {
        return dataSeedService.seedAllData();
    }
}