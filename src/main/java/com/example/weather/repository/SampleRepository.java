package com.example.weather.repository;

import com.example.weather.entity.Sample;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface SampleRepository extends ListCrudRepository<Sample, UUID> {
}
