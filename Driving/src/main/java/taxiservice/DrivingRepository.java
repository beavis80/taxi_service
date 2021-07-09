package taxiservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="drivings", path="drivings")
public interface DrivingRepository extends PagingAndSortingRepository<Driving, Long>{

    Driving findByCarReqId( @Param("car_req_id") Long CarReqId );

}
