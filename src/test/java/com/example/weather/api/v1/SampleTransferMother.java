package com.example.weather.api.v1;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.weather.model.v1.SampleTransfer;

public class SampleTransferMother {

	static SampleTransfer complete() {
		SampleTransfer sample = new SampleTransfer();
		sample.setId(UUID.randomUUID().toString());
		sample.setTemperature(BigDecimal.valueOf(22.8));
		sample.setDateAndTime(LocalDateTime.now());
		sample.setSensorId(4);

		return sample;
	}

	public static SampleTransfer noId() {
		SampleTransfer sample = complete();
		sample.setId(null);

		return sample;
	}
}
