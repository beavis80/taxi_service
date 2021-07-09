package taxiservice;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Driving_table")
public class Driving {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long drivingId;
    private Long carReqId;
    private String drivingStatus;
    private String startTime;
    private String finishTime;
    private Long fee;
    private String userId;
    private String allocStatus;
    private String destAddr;

    @PrePersist
    @PreUpdate
    public void onPrePersist() throws ParseException{

        System.out.println("######################## onPrePersist " + this.getAllocStatus() );

        // 현재시간 구하기 To String
        SimpleDateFormat timeFormat = new SimpleDateFormat ( "yyyyMMddHHmmss");
        Date time = new Date();
        String timeString = timeFormat.format(time);

        System.out.println("######################## onPrePersist " + timeString );

        if ( this.getAllocStatus().equals("Approved")  )
        {
            System.out.println("########################  onPrePersist allocationRequestApproved publishAfterCommit" );
        }
        else if ( this.getAllocStatus().equals("DrivingStarted") )
        {
            System.out.println("########################  onPrePersist drivingStarted publishAfterCommit" );
            this.setStartTime(timeString); 
        }
        else if ( this.getAllocStatus().equals("DrivingFinished") )
        {
            System.out.println("########################  onPrePersist drivingFinished publishAfterCommit" );
            
            Long amount = 1000L;

            this.setFinishTime(timeString);

            // 운임 계산 운행시작시간 - 종료시간 의 차이를 분 당 1000원 으로 계산
            String fromTimeString = this.getStartTime();
            SimpleDateFormat timeFormat1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
            Date fromTime = timeFormat1.parse(fromTimeString);

            amount = ( time.getTime() - fromTime.getTime() ) / (1000*60) * 1000L;

            // 1분 이내라면 1000원 으로 계산
            if ( amount < 1000L ) amount = 1000L;

            this.setFee(amount);
        }
        else return;

    }

    @PostUpdate
    public void onPostUpdate(){

        System.out.println("######################## onPostUpdate " + this.getAllocStatus() );
        
        if ( this.getAllocStatus().equals("Approved")  )
        {
            System.out.println("########################  onPostUpdate allocationRequestApproved publishAfterCommit" );
            AllocationRequestApproved allocationRequestApproved = new AllocationRequestApproved();
            BeanUtils.copyProperties(this, allocationRequestApproved);
            allocationRequestApproved.publishAfterCommit();
        }
        else if ( this.getAllocStatus().equals("DrivingStarted") )
        {
            System.out.println("########################  onPostUpdate drivingStarted publishAfterCommit" );
            DrivingStarted drivingStarted = new DrivingStarted();
            BeanUtils.copyProperties(this, drivingStarted);
            drivingStarted.publishAfterCommit();
        }
        else if ( this.getAllocStatus().equals("DrivingFinished") )
        {
            System.out.println("########################  onPostUpdate drivingFinished publishAfterCommit" );

            // 자동 요금결제 Req-Res
            taxiservice.external.Payment payment = new taxiservice.external.Payment();

            DrivingApplication.applicationContext.getBean(taxiservice.external.PaymentService.class)
                .payFee(this.drivingId, this.fee, "PayRequested");
            
            // 결재처리 후 운행종료
            DrivingFinished drivingFinished = new DrivingFinished();
            BeanUtils.copyProperties(this, drivingFinished);
            drivingFinished.publishAfterCommit();
        }
        else return;

    }


    public Long getDrivingId() {
        return drivingId;
    }

    public void setDrivingId(Long drivingId) {
        this.drivingId = drivingId;
    }
    public Long getCarReqId() {
        return carReqId;
    }

    public void setCarReqId(Long carReqId) {
        this.carReqId = carReqId;
    }
    public String getDrivingStatus() {
        return drivingStatus;
    }

    public void setDrivingStatus(String drivingStatus) {
        this.drivingStatus = drivingStatus;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAllocStatus() {
        return allocStatus;
    }

    public void setAllocStatus(String allocStatus) {
        this.allocStatus = allocStatus;
    }
    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }




}
