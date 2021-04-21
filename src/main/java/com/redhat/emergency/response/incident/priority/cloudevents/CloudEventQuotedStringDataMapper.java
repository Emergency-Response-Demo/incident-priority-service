package com.redhat.emergency.response.incident.priority.cloudevents;

import io.cloudevents.CloudEventData;
import io.cloudevents.rw.CloudEventDataMapper;
import io.cloudevents.rw.CloudEventRWException;

public class CloudEventQuotedStringDataMapper implements CloudEventDataMapper<CloudEventData> {

    @Override
    public CloudEventData map(CloudEventData data) throws CloudEventRWException {
        return StringCloudEventData.wrap(data.toBytes());
    }
}
