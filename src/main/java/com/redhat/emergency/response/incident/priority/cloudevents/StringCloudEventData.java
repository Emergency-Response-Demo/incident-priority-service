package com.redhat.emergency.response.incident.priority.cloudevents;

import java.util.Objects;

import io.cloudevents.CloudEventData;
import org.apache.commons.text.StringEscapeUtils;

public class StringCloudEventData implements CloudEventData {

    private final String value;

    public StringCloudEventData(byte[] bytes) {
        Objects.requireNonNull(bytes);
        String deserialized = new String(bytes);
        if (deserialized.startsWith("\"")) {
            String unescaped = StringEscapeUtils.unescapeJson(deserialized);
            this.value = unescaped.substring(1, unescaped.length()-1);
        } else {
            this.value = deserialized;
        }
    }

    @Override
    public byte[] toBytes() {
        return value.getBytes();
    }

    public String value() {
        return value;
    }

    public static StringCloudEventData wrap(byte[] value) {
        return new StringCloudEventData(value);
    }
}
