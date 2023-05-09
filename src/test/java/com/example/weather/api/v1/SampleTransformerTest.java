package com.example.weather.api.v1;

import com.example.weather.entity.Sample;
import com.example.weather.model.v1.SampleTransfer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SampleTransformerTest {

    @Test
    public void givenEntityIsFilledWhenTransformingShouldCreateTransfer(){
        Sample entity = SampleEntityMother.complete();
        assertEntity(entity);
    }

    private static void assertEntity(Sample entity) {
        SampleTransfer transfer = SampleTransformer.fromEntityToTransfer(entity);
        assertThat(transfer).isNotNull();
        assertThat(transfer.getId()).isEqualTo(entity.getId().toString());
        assertThat(transfer.getTemperature()).isEqualTo(entity.getTemperature());
        assertThat(transfer.getDateAndTime()).isEqualTo(entity.getDateAndTime());
        assertThat(transfer.getSensorId()).isEqualTo(entity.getSensorId());
    }

    @Test
    public void givenEntityHasNullIdWhenTransformingShouldCreateTransferWithoutId(){
        Sample entity = SampleEntityMother.noId();
        SampleTransfer transfer = SampleTransformer.fromEntityToTransfer(entity);
        assertThat(transfer).isNotNull();
        assertThat(transfer.getId()).isNull();
        assertThat(transfer.getTemperature()).isEqualTo(entity.getTemperature());
        assertThat(transfer.getDateAndTime()).isEqualTo(entity.getDateAndTime());
        assertThat(transfer.getSensorId()).isEqualTo(entity.getSensorId());
    }

    @Test
    public void givenEntityListWhenTransformingShouldCreateTransferList(){
        List<Sample> entities = SampleEntityMother.list();
        entities.forEach(SampleTransformerTest::assertEntity);
    }

    @Test
    public void givenTransferIsFilledWhenTransformingShouldCreateEntity(){
        SampleTransfer transfer = SampleTransferMother.complete();
        Sample entity = SampleTransformer.fromTransferToEntity(transfer);
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(UUID.fromString(transfer.getId()));
        assertThat(entity.getTemperature()).isEqualTo(transfer.getTemperature());
        assertThat(entity.getDateAndTime()).isEqualTo(transfer.getDateAndTime());
        assertThat(entity.getSensorId()).isEqualTo(transfer.getSensorId());
    }

    @Test
    public void givenTransferHasNullIdWhenTransformingShouldCreateEntityWithoutId() {
        SampleTransfer transfer = SampleTransferMother.noId();
        Sample entity = SampleTransformer.fromTransferToEntity(transfer);
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNull();
        assertThat(entity.getTemperature()).isEqualTo(transfer.getTemperature());
        assertThat(entity.getDateAndTime()).isEqualTo(transfer.getDateAndTime());
        assertThat(entity.getSensorId()).isEqualTo(transfer.getSensorId());
    }
}
