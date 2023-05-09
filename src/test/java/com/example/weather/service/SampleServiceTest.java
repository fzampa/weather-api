package com.example.weather.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.weather.api.v1.SampleEntityMother;
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
	
	@Test
	void givenRepositoryHasTemperaturesWhenCalculatingAverageThenResultShouldBeAccurate() {
		List<Sample> list = SampleEntityMother.list();
		list.get(0).setTemperature(BigDecimal.valueOf(25));
		list.get(1).setTemperature(BigDecimal.valueOf(25));
		list.get(2).setTemperature(BigDecimal.valueOf(10));
		when(repository.findByDateTimeGreaterThanAndDateTimeLessThan(any(), any())).thenReturn(list);
		OptionalDouble average = service.averageBetween(LocalDateTime.now(), LocalDateTime.now());
		assertTrue(average.isPresent());
		assertThat(average.getAsDouble()).isEqualTo(20);
	}
}
