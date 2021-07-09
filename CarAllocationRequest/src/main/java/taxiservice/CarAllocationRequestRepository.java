package taxiservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="carAllocationRequests", path="carAllocationRequests")
public interface CarAllocationRequestRepository extends PagingAndSortingRepository<CarAllocationRequest, Long>{

    CarAllocationRequest findByCarReqId(Long carReqID);
}
