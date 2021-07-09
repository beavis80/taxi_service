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
 public class PaymentController {

@Autowired
PaymentRepository paymentRepository;

@RequestMapping(value = "/payFee",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

public void payFee(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
                System.out.println("#################### /payments/payFee  called #####");

                Long drivingId = Long.valueOf(request.getParameter("drivingId"));
                Long fee = Long.valueOf(request.getParameter("fee"));
                String paymentStatus = String.valueOf(request.getParameter("paymentStatus"));

                System.out.println("#################### drivingId " + drivingId);
                System.out.println("#################### fee " + fee);
                System.out.println("#################### paymentStatus " + paymentStatus);

                Payment payment = new Payment();

                payment.setDrivingId(drivingId);
                payment.setFee(fee);
                payment.setPaymentStatus(paymentStatus);

                paymentRepository.save(payment);
        }
 }
