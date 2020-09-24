package com.stripe.sample;

import com.stripe.Stripe;
import io.github.cdimascio.dotenv.Dotenv;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentCreateParams.ConfirmationMethod;


public class UPI
{
    public static void main(String[] args)
    {
        try {

        	// Docs: https://stripe.com/docs/payments/upi/accept-a-payment (must be logged in to your account)

            Dotenv dotenv = Dotenv.load();
            Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY_INDIA");

            // Collect the VPA from your customer on your website
            // Test VPAs: https://stripe.com/docs/payments/upi/accept-a-payment#test-your-integration
            String vpa = "payment.success@stripeupi";

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(101099L)
                .setCurrency("inr")
                .addPaymentMethodType("upi")
                .putExtraParam("payment_method_data[upi][vpa]", vpa)
                .putExtraParam("payment_method_data[type]", "upi")
                .setConfirm(true)
                .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println(paymentIntent);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
