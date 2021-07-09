package taxiservice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="DrivingInfo_table")
public class DrivingInfo {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long carReqId;
        private String userId;
        private String destAddr;
        private String allocStatus;
        private Long drivingId;
        private String drivingStatus;
        private String startTime;
        private String finishTime;
        private Long fee;
        private Long paymentId;
        private String paymentStatus;

        public Long getId() {
            return id;
        }

        public void setId(Long carReqId) {
            this.carReqId = id;
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
        public Long getDrivingId() {
            return drivingId;
        }

        public void setDrivingId(Long drivingId) {
            this.drivingId = drivingId;
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
        public Long getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
        }
        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

}
