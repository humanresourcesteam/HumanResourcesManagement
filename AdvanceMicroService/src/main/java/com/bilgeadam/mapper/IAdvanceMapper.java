package com.bilgeadam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAdvanceMapper {

    IAdvanceMapper INSTANCE = Mappers.getMapper(IAdvanceMapper.class);
}
