
package taxiservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

// @FeignClient(name="Driving", url="http://localhost:8083")
@FeignClient(name="payment", url="${feign.client.url.paymentUrl}")
public interface PaymentService {

    @RequestMapping(method= RequestMethod.GET, path="/payFee")
    public void payFee(@RequestParam("drivingId") Long drivingId,
                       @RequestParam("fee") Long fee, 
                       @RequestParam("paymentStatus") String paymentStatus );

}