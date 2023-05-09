package com.example.weather.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.weather.entity.Sample;
import com.example.weather.repository.SampleRepository;

@ExtendWith(MockitoExtension.class)
class SampleServiceTest {
	
	@Mock
	private SampleRepository repository = Mockito.mock(SampleRepository.class);
	
	@InjectMocks
	private SampleService service;

	@Test
	void givenIdDoesntExistWhenDeletingShouldReturnFalse() {
		Optional<Sample> empty = Optional.empty();
		when(repository.findById(any())).thenReturn(empty);
		assertFalse(service.delete(UUID.randomUUID().toString()));
	}
	
	@Test
	void givenIdExistWhenDeletingShouldReturnTrue() {
		Optional<Sample> notEmpty = Optional.of(new Sample());
		when(repository.findById(any())).thenReturn(notEmpty);
		assertTrue(service.delete(UUID.randomUUID().toString()));
	}

}
