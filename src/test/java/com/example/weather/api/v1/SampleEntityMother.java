package com.example.weather.api.v1;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.weather.entity.Sample;

public class SampleEntityMother {

    static Sample complete(){
        Sample sample = new Sample();
        sample.setId(UUID.randomUUID());
        sample.setTemperature(BigDecimal.valueOf(22.8));
        sample.setDateAndTime(LocalDateTime.now());
        sample.setSensorId(3);

        return sample;
    }

    public static Sample noId() {
        Sample sample = complete();
        sample.setId(null);

        return sample;
    }

    public static List<Sample> list() {
        return List.of(complete(), complete(), complete());
    }
}
