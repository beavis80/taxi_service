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
    @Autowired DrivingRepository drivingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAllocationCanceled_DeleteDrivingInfo(@Payload AllocationCanceled allocationCanceled){

        if( allocationCanceled.validate() ) 
        { 
            System.out.println("\n\n##### listener DeleteDrivingInfo : " + allocationCanceled.toJson() + "\n\n");

            // Sample Logic //
            System.out.println("#########################" + allocationCanceled.getCarReqId());
            
            Driving driving = drivingRepository.findByCarReqId(allocationCanceled.getCarReqId());
            drivingRepository.deleteById(driving.getDrivingId());
        }       
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}