package finance.modelling.data.transform.transformtimeseries.service;

import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerTimeSeriesDTO;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.Consumer;

public interface TimeSeriesDataModelService {
    Consumer<KStream<String, EodTickerTimeSeriesDTO>> generateTimeSeriesDataModel();
}
