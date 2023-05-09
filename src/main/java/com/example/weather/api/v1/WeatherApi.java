package com.example.weather.api.v1;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
import com.example.weather.model.v1.AverageResponse;
import com.example.weather.model.v1.SampleSaveTransfer;
import com.example.weather.model.v1.SampleTransfer;
import com.example.weather.service.SampleService;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherApi {

	@Autowired
	private SampleService sampleService;

	@GetMapping("/{id}")
	public ResponseEntity<SampleTransfer> getSample(
			@PathVariable(required = true) String id) {
		Optional<Sample> byId = sampleService.find(id);
		return byId.isPresent()
				? new ResponseEntity<>(
						SampleTransformer.fromEntityToTransfer(byId.get()),
						HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<SampleTransfer>> getAllSamples() {
		List<SampleTransfer> responseList = SampleTransformer
				.fromEntityListToTransferList(sampleService.findAll());
		return responseList.stream().findAny().isPresent()
				? new ResponseEntity<>(responseList, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<?> createSample(
			@RequestBody(required = true) SampleSaveTransfer transfer) {

		Sample entity = SampleTransformer.fromSaveTransferToEntity(transfer);
		sampleService.save(entity);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

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

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSample(
			@PathVariable(required = true) String id) {
		boolean deleted = sampleService.delete(id);
		return deleted
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/average/from/{startDate}/to/{endDate}")
	public ResponseEntity<AverageResponse> average(
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

		AverageResponse response = new AverageResponse();
		OptionalDouble average = sampleService.averageBetweenDates(startDate,endDate);

		response.setAverage(BigDecimal.valueOf(average.isPresent() ? average.getAsDouble() : 0));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/average/sensor/{sensorId}/from/{startDate}/to/{endDate}")
	public ResponseEntity<AverageResponse> averageForSensor(
			@PathVariable(required = true) String sensorId,
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		
		AverageResponse response = new AverageResponse();
		OptionalDouble average = sampleService.averageBetweenDatesForSensor(startDate,endDate, sensorId);
		
		response.setAverage(BigDecimal.valueOf(average.isPresent() ? average.getAsDouble() : 0));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
