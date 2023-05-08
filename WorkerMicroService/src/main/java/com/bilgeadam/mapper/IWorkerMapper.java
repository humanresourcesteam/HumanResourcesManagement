package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.GetAllWorker;
import com.bilgeadam.repository.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IWorkerMapper {

    IWorkerMapper INSTANCE = Mappers.getMapper(IWorkerMapper.class);

    GetAllWorker fromInfoWorker(final Worker worker);
}
