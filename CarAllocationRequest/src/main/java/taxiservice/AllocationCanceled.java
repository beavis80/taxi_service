package taxiservice;

public class AllocationCanceled extends AbstractEvent {

    private Long carReqId;
    private String userId;
    private String allocStatus;

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
    public String getAllocStatus() {
        return allocStatus;
    }

    public void setAllocStatus(String allocStatus) {
        this.allocStatus = allocStatus;
    }
}