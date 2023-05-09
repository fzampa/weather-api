package com.example.weather.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.weather.entity.Sample;

public interface SampleRepository extends ListCrudRepository<Sample, UUID> {
}
