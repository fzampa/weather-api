package com.example.weather.api.v1;

import com.example.weather.entity.Sample;
import com.example.weather.model.v1.SampleTransfer;
import com.example.weather.repository.SampleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherApi {

    @Autowired
    private SampleRepository sampleRepository;

    @Operation(summary = "Return specific sample based on it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sample with given ID", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                            implementation = SampleTransfer.class))),
            @ApiResponse(responseCode = "404", description = "Sample not found", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/{id}")
    public SampleTransfer getSample(@PathVariable(required = true) String id) {
        Optional<Sample> byId = sampleRepository.findById(UUID.fromString(id));
        return byId.isPresent() ? SampleTransformer.fromEntityToTransfer(byId.get()) : null; //imlement 404
    }

    @Operation(summary = "Return all samples")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of samples", content = @Content(
                    mediaType = "application/json", array = @ArraySchema(schema = @Schema(
                            implementation = SampleTransfer.class)))),
            @ApiResponse(responseCode = "404", description = "Sample not found", content = @Content(
                    mediaType = "application/json"))
    })
    @GetMapping
    public List<SampleTransfer> getAllSamples() {
        return SampleTransformer.fromEntityListToTransferList(sampleRepository.findAll());
    }

    @Operation(summary = "Creates a sample")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Sample to be created", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                            implementation = SampleTransfer.class)), required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public void createSample(@RequestBody(required = true) SampleTransfer transfer) {
        transfer.setId(null);
        Sample entity = SampleTransformer.fromTransferToEntity(transfer);
        sampleRepository.save(entity);
    }
}
