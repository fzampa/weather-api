package com.example.weather.api.v1;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.weather.entity.Sample;
import com.example.weather.model.v1.SampleSaveTransfer;
import com.example.weather.model.v1.SampleTransfer;

public class SampleTransformer {
	public static SampleTransfer fromEntityToTransfer(Sample entity) {
		SampleTransfer transfer = new SampleTransfer();
		if (entity.getId() != null) {
			transfer.setId(entity.getId().toString());
		}
		transfer.setTemperature(entity.getTemperature());
		transfer.setDateAndTime(entity.getDateTime());
		transfer.setSensorId(entity.getSensorId());

		return transfer;
	}

	public static List<SampleTransfer> fromEntityListToTransferList(List<Sample> entities) {
		if (entities == null) {
			return Collections.emptyList();
		}
		return entities.stream().map(SampleTransformer::fromEntityToTransfer).collect(Collectors.toList());
	}

	public static Sample fromTransferToEntity(SampleTransfer transfer) {
		Sample entity = new Sample();
		entity.setId(transfer.getId() == null ? null : UUID.fromString(transfer.getId()));
		entity.setTemperature(transfer.getTemperature());
		entity.setDateTime(transfer.getDateAndTime());
		entity.setSensorId(transfer.getSensorId());

		return entity;
	}

	public static Sample fromSaveTransferToEntity(SampleSaveTransfer transfer) {
		Sample entity = new Sample();

		entity.setId(null);
		entity.setTemperature(transfer.getTemperature());
		entity.setDateTime(transfer.getDateAndTime());
		entity.setSensorId(transfer.getSensorId());

		return entity;
	}
}
