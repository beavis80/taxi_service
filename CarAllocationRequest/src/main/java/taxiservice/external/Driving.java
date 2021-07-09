package taxiservice.external;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driving {

    private Long drivingId;
    private Long carReqId;
    private String drivingStatus;
    private String startTime;
    private String finishTime;
    private Long fee;
    private String userId;
    private String allocStatus;
    private String destAddr;

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
