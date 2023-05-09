package com.example.weather.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weather.entity.Sample;
import com.example.weather.repository.SampleRepository;

@Service
public class SampleService {
	
	@Autowired
	private SampleRepository sampleRepository;

	public Optional<Sample> find(String id) {
		return sampleRepository.findById(UUID.fromString(id));
	}

	public List<Sample> findAll() {
		return sampleRepository.findAll();
	}

	public Sample save(Sample entity) {
		return sampleRepository.save(entity);
	}
	
	public boolean delete(String id) {
		if (find(id).isEmpty()) {
			return false;
		}

		sampleRepository.deleteById(UUID.fromString(id));
		return true;
	}

	public OptionalDouble averageBetween(LocalDateTime startDate,
			LocalDateTime endDate) {
		List<Sample> samples = sampleRepository.findByDateTimeGreaterThanAndDateTimeLessThan(startDate, endDate);
		return samples.stream().mapToDouble(s -> s.getTemperature().doubleValue()).average();
	}
}
