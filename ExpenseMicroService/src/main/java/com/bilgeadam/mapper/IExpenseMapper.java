package com.bilgeadam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IExpenseMapper {

    IExpenseMapper INSTANCE = Mappers.getMapper(IExpenseMapper.class);
}
