package taxiservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DrivingInfoRepository extends CrudRepository<DrivingInfo, Long> {

    List<DrivingInfo> findByDrivingId(Long drivingId);

    Optional<DrivingInfo> findByCarReqId(Long carReqId);
    
    boolean deleteByCarReqId(Long carReqId);
}