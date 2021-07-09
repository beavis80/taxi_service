package taxiservice;

import taxiservice.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DrivingInfoViewHandler {


    @Autowired
    private DrivingInfoRepository drivingInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAllocationRequested_then_CREATE_1 (@Payload AllocationRequested allocationRequested) {
        try {

            if (!allocationRequested.validate()) return;

            // view 객체 생성
            DrivingInfo drivingInfo = new DrivingInfo();
            // view 객체에 이벤트의 Value 를 set 함
            drivingInfo.setCarReqId(allocationRequested.getCarReqId());
            drivingInfo.setUserId(allocationRequested.getUserId());
            drivingInfo.setAllocStatus(allocationRequested.getAllocStatus());
            drivingInfo.setDestAddr(allocationRequested.getDestAddr());
            // view 레파지 토리에 save
            drivingInfoRepository.save(drivingInfo);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenAllocationRequestApproved_then_UPDATE_1(@Payload AllocationRequestApproved allocationRequestApproved) {
        try {
            if (!allocationRequestApproved.validate()) return;
                // view 객체 조회
            Optional<DrivingInfo> drivingInfoOptional = drivingInfoRepository.findByCarReqId(allocationRequestApproved.getCarReqId());
            if( drivingInfoOptional.isPresent()) {
                DrivingInfo drivingInfo = drivingInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                    drivingInfo.setDrivingId(allocationRequestApproved.getDrivingId());
                    drivingInfo.setDrivingStatus(allocationRequestApproved.getDrivingStatus());
                    drivingInfo.setAllocStatus(allocationRequestApproved.getAllocStatus());
                // view 레파지 토리에 save
                drivingInfoRepository.save(drivingInfo);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDrivingStarted_then_UPDATE_2(@Payload DrivingStarted drivingStarted) {
        try {
            if (!drivingStarted.validate()) return;
                // view 객체 조회
            Optional<DrivingInfo> drivingInfoOptional = drivingInfoRepository.findByCarReqId(drivingStarted.getCarReqId());
            if( drivingInfoOptional.isPresent()) {
                DrivingInfo drivingInfo = drivingInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                    drivingInfo.setDrivingStatus(drivingStarted.getDrivingStatus());
                    drivingInfo.setStartTime(drivingStarted.getStartTime());
                    drivingInfo.setAllocStatus(drivingStarted.getAllocStatus());
                // view 레파지 토리에 save
                drivingInfoRepository.save(drivingInfo);
            }
            List<DrivingInfo> drivingInfoList = drivingInfoRepository.findByDrivingId(drivingStarted.getDrivingId());
            for(DrivingInfo drivingInfo : drivingInfoList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                drivingInfo.setDrivingStatus(drivingStarted.getDrivingStatus());
                drivingInfo.setStartTime(drivingStarted.getStartTime());
                drivingInfo.setAllocStatus(drivingStarted.getAllocStatus());
                // view 레파지 토리에 save
                drivingInfoRepository.save(drivingInfo);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDrivingFinished_then_UPDATE_3(@Payload DrivingFinished drivingFinished) {
        try {
            if (!drivingFinished.validate()) return;
                // view 객체 조회
            Optional<DrivingInfo> drivingInfoOptional = drivingInfoRepository.findByCarReqId(drivingFinished.getCarReqId());
            if( drivingInfoOptional.isPresent()) {
                DrivingInfo drivingInfo = drivingInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                    drivingInfo.setDrivingStatus(drivingFinished.getDrivingStatus());
                    drivingInfo.setFinishTime(drivingFinished.getFinishTime());
                    drivingInfo.setFee(drivingFinished.getFee());
                    drivingInfo.setAllocStatus(drivingFinished.getAllocStatus());
                // view 레파지 토리에 save
                drivingInfoRepository.save(drivingInfo);
            }
            List<DrivingInfo> drivingInfoList = drivingInfoRepository.findByDrivingId(drivingFinished.getDrivingId());
            for(DrivingInfo drivingInfo : drivingInfoList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                drivingInfo.setDrivingStatus(drivingFinished.getDrivingStatus());
                drivingInfo.setFinishTime(drivingFinished.getFinishTime());
                drivingInfo.setFee(drivingFinished.getFee());
                drivingInfo.setAllocStatus(drivingFinished.getAllocStatus());
                // view 레파지 토리에 save
                drivingInfoRepository.save(drivingInfo);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayApproved_then_UPDATE_4(@Payload PayApproved payApproved) {
        try {
            if (!payApproved.validate()) return;
                // view 객체 조회
            List<DrivingInfo> drivingInfoList = drivingInfoRepository.findByDrivingId(payApproved.getDrivingId());
            for(DrivingInfo drivingInfo : drivingInfoList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                drivingInfo.setPaymentId(payApproved.getPaymentId());
                drivingInfo.setPaymentStatus(payApproved.getPaymentStatus());
                // view 레파지 토리에 save
                drivingInfoRepository.save(drivingInfo);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAllocationCanceled_then_DELETE_1(@Payload AllocationCanceled allocationCanceled) {
        try {
            if (!allocationCanceled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            Optional<DrivingInfo> drivingInfoOptional = drivingInfoRepository.findByCarReqId(allocationCanceled.getCarReqId());

            if( drivingInfoOptional.isPresent()) {
                DrivingInfo drivingInfo = drivingInfoOptional.get();
                drivingInfoRepository.deleteById(drivingInfo.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}