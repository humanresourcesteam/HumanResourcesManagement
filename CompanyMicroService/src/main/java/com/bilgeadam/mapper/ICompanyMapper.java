package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.GetAllInfoCompany;
import com.bilgeadam.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ICompanyMapper {

    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);

    GetAllInfoCompany fromInfoCompany(final Company company);
}
