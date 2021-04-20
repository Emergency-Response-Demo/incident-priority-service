package com.redhat.emergency.response.incident.priority.cloudevents;

import java.util.Map;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import io.cloudevents.core.message.MessageReader;
import io.cloudevents.kafka.KafkaMessageFactory;
import io.cloudevents.rw.CloudEventDataMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudEventDeserializer implements Deserializer<CloudEvent> {

    private static Logger log = LoggerFactory.getLogger(CloudEventDeserializer.class);

    private final CloudEventDataMapper<CloudEventData> mapper;

    public CloudEventDeserializer() {
        mapper = new CloudEventQuotedStringDataMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public CloudEvent deserialize(String topic, byte[] data) {
        throw new UnsupportedOperationException("CloudEventDeserializer supports only the signature deserialize(String, Headers, byte[])");
    }

    @Override
    public CloudEvent deserialize(String topic, Headers headers, byte[] data) {
        try {
            MessageReader reader = KafkaMessageFactory.createReader(headers, data);
            return reader.toEvent(mapper);
        } catch (Exception e) {
            log.error("Error deserializing CloudEvent", e);
        }
        return null;
    }

}
