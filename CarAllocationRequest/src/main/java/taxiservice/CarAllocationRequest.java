package taxiservice;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="CarAllocationRequest_table")
public class CarAllocationRequest {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long carReqId;
    private String userId;
    private String destAddr;
    private String allocStatus;

    @PostPersist
    public void onPostPersist(){

        System.out.println("######################### 부하 테스트 - allocRequest start" );
        CarAllocationRequestApplication.applicationContext.getBean(taxiservice.external.DrivingService.class)
                        .allocRequest(this.getCarReqId(), this.getDestAddr(), this.getAllocStatus(), this.getUserId());


        System.out.println("######################### 부하 테스트 - allocRequest end " );

        AllocationRequested allocationRequested = new AllocationRequested();
        BeanUtils.copyProperties(this, allocationRequested);
        allocationRequested.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove(){
        AllocationCanceled allocationCanceled = new AllocationCanceled();
        BeanUtils.copyProperties(this, allocationCanceled);
        allocationCanceled.publishAfterCommit();
    }


    public Long getCarReqId() {
        return carReqId;
    }

    public void setCarReqId(Long carReqId) {
        this.carReqId = carReqId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }
    public String getAllocStatus() {
        return allocStatus;
    }

    public void setAllocStatus(String allocStatus) {
        this.allocStatus = allocStatus;
    }


}
