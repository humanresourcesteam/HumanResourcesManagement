package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.DetailResponseDto;
import com.bilgeadam.repository.entity.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T20:46:13+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class IAdminMapperImpl implements IAdminMapper {

    @Override
    public DetailResponseDto toDetailResponseDto(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        DetailResponseDto.DetailResponseDtoBuilder detailResponseDto = DetailResponseDto.builder();

        detailResponseDto.firstName( admin.getFirstName() );
        detailResponseDto.surname( admin.getSurname() );
        detailResponseDto.dateOfEmployment( admin.getDateOfEmployment() );
        detailResponseDto.email( admin.getEmail() );
        detailResponseDto.image( admin.getImage() );

        return detailResponseDto.build();
    }
}
