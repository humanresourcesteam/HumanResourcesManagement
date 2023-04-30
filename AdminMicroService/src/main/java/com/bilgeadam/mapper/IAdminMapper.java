package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.DetailResponseDto;
import com.bilgeadam.dto.response.SummaryResponseDto;
import com.bilgeadam.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);

    DetailResponseDto toDetailResponseDto(final Admin admin);

}
