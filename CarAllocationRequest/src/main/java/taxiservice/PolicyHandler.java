package taxiservice;

import taxiservice.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired CarAllocationRequestRepository carAllocationRequestRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAllocationRequestApproved_UpdateAllocation(@Payload AllocationRequestApproved allocationRequestApproved){

        if(!allocationRequestApproved.validate()) return;

        System.out.println("\n\n##### listener UpdateAllocation : " + allocationRequestApproved.toJson() + "\n\n");

        // Sample Logic //
        CarAllocationRequest carAllocationRequest = carAllocationRequestRepository.findByCarReqId( allocationRequestApproved.getCarReqId() );
        carAllocationRequest.setAllocStatus( allocationRequestApproved.getAllocStatus() );
        carAllocationRequestRepository.save(carAllocationRequest);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDrivingStarted_UpdateAllocation(@Payload DrivingStarted drivingStarted){

        if(!drivingStarted.validate()) return;

        System.out.println("\n\n##### listener UpdateAllocation : " + drivingStarted.toJson() + "\n\n");

        // Sample Logic //
        CarAllocationRequest carAllocationRequest = carAllocationRequestRepository.findByCarReqId( drivingStarted.getCarReqId() );
        carAllocationRequest.setAllocStatus( drivingStarted.getAllocStatus() );
        carAllocationRequestRepository.save(carAllocationRequest);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDrivingFinished_UpdateAllocation(@Payload DrivingFinished drivingFinished){

        if(!drivingFinished.validate()) return;

        System.out.println("\n\n##### listener UpdateAllocation : " + drivingFinished.toJson() + "\n\n");

        // Sample Logic //
        CarAllocationRequest carAllocationRequest = carAllocationRequestRepository.findByCarReqId( drivingFinished.getCarReqId() );
        carAllocationRequest.setAllocStatus( drivingFinished.getAllocStatus() );
        carAllocationRequestRepository.save(carAllocationRequest);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
