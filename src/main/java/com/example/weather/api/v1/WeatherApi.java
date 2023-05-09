package com.example.weather.api.v1;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather.entity.Sample;
import com.example.weather.model.v1.SampleSaveTransfer;
import com.example.weather.model.v1.SampleTransfer;
import com.example.weather.service.SampleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherApi {

	@Autowired
	private SampleService sampleService;

	@Operation(summary = "Return specific sample based on it's id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sample with given ID", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SampleTransfer.class))),
			@ApiResponse(responseCode = "404", description = "Sample not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
	@GetMapping("/{id}")
	public ResponseEntity<SampleTransfer> getSample(
			@PathVariable(required = true) String id) {
		Optional<Sample> byId = sampleService.find(id);
		return byId.isPresent()
				? new ResponseEntity<>(SampleTransformer.fromEntityToTransfer(byId.get()),HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Return all samples")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of samples", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SampleTransfer.class)))),
			@ApiResponse(responseCode = "404", description = "Sample not found", content = @Content(mediaType = "application/json"))})
	@GetMapping
	public ResponseEntity<List<SampleTransfer>> getAllSamples() {
		List<SampleTransfer> responseList = SampleTransformer
				.fromEntityListToTransferList(sampleService.findAll());
		return responseList.stream().findAny().isPresent()
				? new ResponseEntity<>(responseList, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Creates a sample")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Sample to be created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SampleSaveTransfer.class)), required = true)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
	@PostMapping
	public ResponseEntity<?> createSample(
			@RequestBody(required = true) SampleSaveTransfer transfer) {

		Sample entity = SampleTransformer.fromSaveTransferToEntity(transfer);
		sampleService.save(entity);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "Updates a sample")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Sample to be updated, with ID", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SampleTransfer.class)), required = true)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sample update", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Sample not found", content = @Content(mediaType = "application/json"))})
	@PutMapping
	public ResponseEntity<?> updateSample(
			@RequestBody(required = true) SampleTransfer transfer) {

		if (Objects.isNull(transfer.getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Sample entity = SampleTransformer.fromTransferToEntity(transfer);
		sampleService.save(entity);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Deletes a sample based on it's id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Deleted"),
			@ApiResponse(responseCode = "404", description = "Not found")})
	@DeleteMapping
	public ResponseEntity<?> deleteSample(
			@PathVariable(required = true) String id) {
		boolean deleted = sampleService.delete(id);
		return deleted
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
