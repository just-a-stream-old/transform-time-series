package finance.modelling.data.transform.transformtimeseries.repository.mapper;

import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerTimeSeriesDTO;
import finance.modelling.fmcommons.data.schema.model.TimeSeriesD1;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TimeSeriesMapper {
    TimeSeriesMapper INSTANCE = Mappers.getMapper(TimeSeriesMapper.class);

    TimeSeriesD1 timeSeriesDTOToTimeSeries(EodTickerTimeSeriesDTO eodTimeSeriesDTO);
}
