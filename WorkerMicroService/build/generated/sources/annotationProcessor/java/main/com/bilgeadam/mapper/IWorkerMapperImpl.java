package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.GetAllWorker;
import com.bilgeadam.repository.entity.Worker;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-09T20:16:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class IWorkerMapperImpl implements IWorkerMapper {

    @Override
    public GetAllWorker fromInfoWorker(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        GetAllWorker.GetAllWorkerBuilder getAllWorker = GetAllWorker.builder();

        getAllWorker.companyid( worker.getCompanyid() );
        getAllWorker.managerid( worker.getManagerid() );
        getAllWorker.name( worker.getName() );
        getAllWorker.secondname( worker.getSecondname() );
        getAllWorker.surname( worker.getSurname() );
        getAllWorker.secondSurname( worker.getSecondSurname() );
        getAllWorker.birthDate( worker.getBirthDate() );
        getAllWorker.birthPlace( worker.getBirthPlace() );
        getAllWorker.identificationNumber( worker.getIdentificationNumber() );
        getAllWorker.dateOfEmployment( worker.getDateOfEmployment() );
        getAllWorker.terminationDate( worker.getTerminationDate() );
        getAllWorker.activity( worker.getActivity() );
        getAllWorker.occupation( worker.getOccupation() );
        getAllWorker.email( worker.getEmail() );
        getAllWorker.address( worker.getAddress() );
        getAllWorker.companyPhone( worker.getCompanyPhone() );
        getAllWorker.image( worker.getImage() );

        return getAllWorker.build();
    }
}
