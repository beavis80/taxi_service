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
    @Autowired MessageRepository messageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAllocationRequested_SendMsg(@Payload AllocationRequested allocationRequested){

        if(!allocationRequested.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + allocationRequested.toJson() + "\n\n");

        // Sample Logic //
        Message message = new Message();
        message.setCarReqId(allocationRequested.getCarReqId());
        message.setUserId(allocationRequested.getUserId());
        message.setMsgContext("차량 배정이 요청 되었습니다.");
        messageRepository.save(message);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAllocationCanceled_SendMsg(@Payload AllocationCanceled allocationCanceled){

        if(!allocationCanceled.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + allocationCanceled.toJson() + "\n\n");

        // Sample Logic //
        Message message = new Message();
        message.setCarReqId(allocationCanceled.getCarReqId());
        message.setUserId(allocationCanceled.getUserId());
        message.setMsgContext("차량 배정 요청이 취소 되었습니다.");
        messageRepository.save(message);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAllocationRequestApproved_SendMsg(@Payload AllocationRequestApproved allocationRequestApproved){

        if(!allocationRequestApproved.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + allocationRequestApproved.toJson() + "\n\n");

        // Sample Logic //
        Message message = new Message();
        message.setCarReqId(allocationRequestApproved.getCarReqId());
        message.setDrivingId(allocationRequestApproved.getDrivingId());
        message.setMsgContext("차량 배정 요청이 승인 되었습니다.");
        messageRepository.save(message);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayApproved_SendMsg(@Payload PayApproved payApproved){

        if(!payApproved.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + payApproved.toJson() + "\n\n");

        // Sample Logic //
        Message message = new Message();
        message.setCarReqId(payApproved.getDrivingId());
        Long payamount = payApproved.getFee();
        message.setMsgContext("요금 " + payamount + "원 이 결제되었습니다.");
        messageRepository.save(message);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDrivingFinished_SendMsg(@Payload DrivingFinished drivingFinished){

        if(!drivingFinished.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + drivingFinished.toJson() + "\n\n");

        // Sample Logic //
        Message message = new Message();
        message.setCarReqId(drivingFinished.getCarReqId());
        message.setDrivingId(drivingFinished.getDrivingId());
        message.setUserId(drivingFinished.getUserId());
        message.setMsgContext("운행이 종료 되었습니다.");
        messageRepository.save(message);
            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDrivingStarted_SendMsg(@Payload DrivingStarted drivingStarted){

        if(!drivingStarted.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + drivingStarted.toJson() + "\n\n");

        // Sample Logic //
        Message message = new Message();
        message.setCarReqId(drivingStarted.getCarReqId());
        message.setDrivingId(drivingStarted.getDrivingId());
        message.setUserId(drivingStarted.getUserId());
        message.setMsgContext("운행이 시작 되었습니다.");
        messageRepository.save(message);
            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
