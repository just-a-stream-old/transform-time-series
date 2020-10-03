package finance.modelling.data.transform.transformtimeseries.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TopicConfig {

    private final String traceIdHeaderName;
    private final String ingestEodTimeSeriesD1Topic;

    public TopicConfig(
            @Value("${spring.cloud.stream.kafka.streams.header.traceId}") String traceIdHeaderName,
            @Value("${spring.cloud.stream.bindings.generateTimeSeriesDataModel-in-0.destination}") String ingestEodTimeSeriesD1Topic) {
        this.traceIdHeaderName = traceIdHeaderName;
        this.ingestEodTimeSeriesD1Topic = ingestEodTimeSeriesD1Topic;
    }
}
