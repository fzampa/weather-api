package com.example.weather.api.v1;

import com.example.weather.entity.Sample;
import com.example.weather.model.v1.SampleTransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SampleTransformer {
    public static SampleTransfer fromEntityToTransfer(Sample entity) {
        SampleTransfer transfer = new SampleTransfer();
        if (entity.getId() != null) {
            transfer.setId(entity.getId().toString());
        }
        transfer.setTemperature(entity.getTemperature());
        transfer.setDateAndTime(entity.getDateAndTime());

        return transfer;
    }

    public static List<SampleTransfer> fromEntityListToTransferList(List<Sample> entities) {
        return entities.stream().map(SampleTransformer::fromEntityToTransfer).collect(Collectors.toList());
    }

    public static Sample fromTransferToEntity(SampleTransfer transfer) {
        Sample entity = new Sample();
        entity.setId(transfer.getId() == null ? null : UUID.fromString(transfer.getId()));
        entity.setTemperature(transfer.getTemperature());
        entity.setDateAndTime(transfer.getDateAndTime());

        return entity;
    }
}
