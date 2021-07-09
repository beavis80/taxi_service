
package taxiservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// @FeignClient(name="Driving", url="http://localhost:8086")
@FeignClient(name="driving", url="${feign.client.url.drivingUrl}")
public interface DrivingService {

    @RequestMapping(method= RequestMethod.GET, path="/allocRequest")
    public void allocRequest(@RequestParam("carReqId") Long carReqId,
                             @RequestParam("destAddr") String destAddr, 
                             @RequestParam("allocStatus") String allocStatus,
                             @RequestParam("userId") String userId );

}
