package finance.modelling.data.transform.transformtimeseries.repository.mapper;

import finance.modelling.fmcommons.data.schema.eod.dto.EodDateOHLCAVDTO;
import finance.modelling.fmcommons.data.schema.eod.dto.EodTickerTimeSeriesDTO;
import finance.modelling.fmcommons.data.schema.model.DateOLHCAV;
import finance.modelling.fmcommons.data.schema.model.TimeSeriesD1;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TimeSeriesMapper {
    TimeSeriesMapper INSTANCE = Mappers.getMapper(TimeSeriesMapper.class);

    TimeSeriesD1 timeSeriesDTOToTimeSeries(EodTickerTimeSeriesDTO eodTimeSeriesDTO);

    DateOLHCAV balanceSheetDTOToBalanceSheet(EodDateOHLCAVDTO eodDateOHLCVDTO);

    @AfterMapping
    default void determineSymbolField(EodTickerTimeSeriesDTO dto, @MappingTarget DateOLHCAV dateOLHCAV) {
        dateOLHCAV.setSymbol(dto.getSymbol());
    }



}
