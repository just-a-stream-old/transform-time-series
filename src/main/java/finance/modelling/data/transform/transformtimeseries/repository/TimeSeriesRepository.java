package finance.modelling.data.transform.transformtimeseries.repository;

import finance.modelling.fmcommons.data.schema.model.TimeSeriesD1;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimeSeriesRepository extends ReactiveMongoRepository<TimeSeriesD1, UUID> {
}
