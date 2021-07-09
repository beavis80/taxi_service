package taxiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class DrivingController {

    @Autowired
    DrivingRepository drivingRepository;
    
    @RequestMapping(value = "/allocRequest",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public void allocRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

                System.out.println("#################### /allocRequest called ");

                Long carReqId = Long.valueOf(request.getParameter("carReqId"));
                String destAddr = String.valueOf(request.getParameter("destAddr"));
                String allocStatus = String.valueOf(request.getParameter("allocStatus"));
                String userId = String.valueOf(request.getParameter("userId"));

                System.out.println("#################### carReqId " + carReqId);
                System.out.println("#################### destAddr " + destAddr);
                System.out.println("#################### allocStatus " + allocStatus);
    
                Driving driving = new Driving();

                driving.setCarReqId(carReqId);
                driving.setDestAddr(destAddr);
                driving.setAllocStatus(allocStatus);
                driving.setUserId(userId);

                drivingRepository.save(driving);

            }

 }

