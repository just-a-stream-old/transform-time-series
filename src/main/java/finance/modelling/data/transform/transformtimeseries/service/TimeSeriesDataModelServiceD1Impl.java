package finance.modelling.data.transform.transformtimeseries.service;

import finance.modelling.data.transform.transformtimeseries.repository.TimeSeriesRepository;
import finance.modelling.data.transform.transformtimeseries.repository.config.MongoDbConfig;
import finance.modelling.data.transform.transformtimeseries.repository.mapper.TimeSeriesMapper;
import finance.modelling.data.transform.transformtimeseries.service.config.TopicConfig;
import finance.modelling.fmcommons.data.logging.LogDb;
import finance.modelling.fmcommons.data.logging.kstream.LogMessageConsumed;
import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerTimeSeriesDTO;
import finance.modelling.fmcommons.data.schema.model.TimeSeriesD1;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Service
public class TimeSeriesDataModelServiceD1Impl implements TimeSeriesDataModelService {

    private final TopicConfig topics;
    private final TimeSeriesRepository timeSeriesRepository;
    private final MongoDbConfig dbConfig;

    public TimeSeriesDataModelServiceD1Impl(
            TopicConfig topics,
            TimeSeriesRepository timeSeriesRepository,
            MongoDbConfig dbConfig) {
        this.topics = topics;
        this.timeSeriesRepository = timeSeriesRepository;
        this.dbConfig = dbConfig;
    }

    @Bean
    public Consumer<KStream<String, EodTickerTimeSeriesDTO>> generateTimeSeriesDataModel() {
        return eodTimeSeriesD1 -> eodTimeSeriesD1
                .transformValues(() -> new LogMessageConsumed<>(topics.getTraceIdHeaderName()))
                .mapValues(TimeSeriesMapper.INSTANCE::timeSeriesDTOToTimeSeries)
                .foreach(this::saveToTimeSeriesD1Repository);
    }

    protected void saveToTimeSeriesD1Repository(String _key, TimeSeriesD1 timeSeriesD1) {
        Mono
                .just(timeSeriesD1)
                .flatMap(timeSeriesRepository::save)
                .subscribe(
                        timeSeriesMono -> LogDb.logInfoDataItemSaved(
                                TimeSeriesD1.class, timeSeriesMono.getSymbol(), dbConfig.getDbUri()),
                        error -> LogDb.logErrorFailedDataItemSave(
                                TimeSeriesD1.class, error, dbConfig.getDbUri(), List.of("Log and fail"))
                );
    }
}
