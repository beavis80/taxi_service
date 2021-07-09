package taxiservice;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long paymentId;
    private Long drivingId;
    private Long fee;
    private String paymentStatus;

    @PrePersist
    public void onPrePersist(){
        PayApproved payApproved = new PayApproved();

        payApproved.setPaymentId(this.getPaymentId());
        payApproved.setDrivingId(this.getDrivingId());
        payApproved.setFee(this.getFee());
        this.setPaymentStatus("PaymentApproved");
        payApproved.setPaymentStatus(this.getPaymentStatus());

        BeanUtils.copyProperties(this, payApproved);
        payApproved.publishAfterCommit();

        System.out.println("######################## onPostPersist  Payment publishAfterCommit"  );
    }


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Long getDrivingId() {
        return drivingId;
    }

    public void setDrivingId(Long drivingId) {
        this.drivingId = drivingId;
    }
    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }




}
