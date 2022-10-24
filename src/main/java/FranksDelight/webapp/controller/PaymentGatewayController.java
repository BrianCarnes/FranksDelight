package FranksDelight.webapp.controller;

import FranksDelight.webapp.Security.PropertiesReader;
import FranksDelight.webapp.configuration.StripeClient;
import FranksDelight.webapp.response.StripeResponse;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentGatewayController {

    @PostMapping("/charge")
    public String createPaymentIntent(@RequestParam("amount") Long amount) throws StripeException {
        Gson gson = new Gson();
        Stripe.apiKey = PropertiesReader.getProperty("SECRET_KEY");

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .setCurrency("USD")
                .setAmount(amount)
                .build();

        try {
            // Create a PaymentIntent with the order amount and currency
            PaymentIntent intent = PaymentIntent.create(params);

            // Send PaymentIntent details to client
            return gson.toJson(new StripeResponse(intent.getClientSecret()));
        } catch(StripeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}