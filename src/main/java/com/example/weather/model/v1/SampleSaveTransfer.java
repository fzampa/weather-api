package com.example.weather.model.v1;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SampleSaveTransfer {

	private BigDecimal temperature;
	private LocalDateTime dateAndTime;
	private Integer sensorId;

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public Integer getSensorId() {
		return sensorId;
	}
	
	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}
}
