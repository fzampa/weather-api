package com.example.weather.api.v1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.weather.entity.Sample;
import com.example.weather.model.v1.SampleTransfer;

class SampleTransformerTest {

	@Test
	public void givenEntityIsFilledWhenTransformingShouldCreateTransfer() {
		Sample entity = SampleEntityMother.complete();
		SampleTransfer transfer = SampleTransformer.fromEntityToTransfer(entity);
		assertEntityCorrectlyTransformedIntoTransfer(entity, transfer);
	}

	private void assertEntityCorrectlyTransformedIntoTransfer(Sample origin, SampleTransfer destination) {
		assertThat(destination).isNotNull();
		assertThat(destination.getId()).isEqualTo(origin.getId().toString());
		assertThat(destination.getTemperature()).isEqualTo(origin.getTemperature());
		assertThat(destination.getDateAndTime()).isEqualTo(origin.getDateTime());
		assertThat(destination.getSensorId()).isEqualTo(origin.getSensorId());
	}

	@Test
	public void givenEntityHasNullIdWhenTransformingShouldCreateTransferWithoutId() {
		Sample entity = SampleEntityMother.noId();
		SampleTransfer transfer = SampleTransformer.fromEntityToTransfer(entity);
		assertThat(transfer).isNotNull();
		assertThat(transfer.getId()).isNull();
		assertThat(transfer.getTemperature()).isEqualTo(entity.getTemperature());
		assertThat(transfer.getDateAndTime()).isEqualTo(entity.getDateTime());
		assertThat(transfer.getSensorId()).isEqualTo(entity.getSensorId());
	}

	@Test
	public void givenEntityListWhenTransformingShouldCreateTransferList() {
		List<Sample> entities = SampleEntityMother.list();
		List<SampleTransfer> transfers = SampleTransformer.fromEntityListToTransferList(entities);

		for (int i = 0; i < transfers.size(); i++) {
			assertEntityCorrectlyTransformedIntoTransfer(entities.get(i), transfers.get(i));
		}
	}

	@Test
	public void givenNullListWhenTransformingShouldCreateEmptyTransferList() {
		assertThat(SampleTransformer.fromEntityListToTransferList(null)).isEmpty();
	}

	@Test
	public void givenTransferIsFilledWhenTransformingShouldCreateEntity() {
		SampleTransfer transfer = SampleTransferMother.complete();
		Sample entity = SampleTransformer.fromTransferToEntity(transfer);
		assertThat(entity).isNotNull();
		assertThat(entity.getId()).isEqualTo(UUID.fromString(transfer.getId()));
		assertThat(entity.getTemperature()).isEqualTo(transfer.getTemperature());
		assertThat(entity.getDateTime()).isEqualTo(transfer.getDateAndTime());
		assertThat(entity.getSensorId()).isEqualTo(transfer.getSensorId());
	}

	@Test
	public void givenTransferHasNullIdWhenTransformingShouldCreateEntityWithoutId() {
		SampleTransfer transfer = SampleTransferMother.noId();
		Sample entity = SampleTransformer.fromTransferToEntity(transfer);
		assertThat(entity).isNotNull();
		assertThat(entity.getId()).isNull();
		assertThat(entity.getTemperature()).isEqualTo(transfer.getTemperature());
		assertThat(entity.getDateTime()).isEqualTo(transfer.getDateAndTime());
		assertThat(entity.getSensorId()).isEqualTo(transfer.getSensorId());
	}
}
