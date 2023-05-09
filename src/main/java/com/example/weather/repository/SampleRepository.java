package com.example.weather.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.weather.entity.Sample;

public interface SampleRepository extends ListCrudRepository<Sample, UUID> {
	
	List<Sample> findByDateTimeGreaterThanAndDateTimeLessThan(LocalDateTime start, LocalDateTime end);

	List<Sample> findByDateTimeGreaterThanAndDateTimeLessThanAndSensorIdEquals(
			LocalDateTime startDate, LocalDateTime endDate, String sensorId);
}
