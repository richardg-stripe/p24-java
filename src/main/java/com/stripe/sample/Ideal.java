package com.stripe.sample;

import com.stripe.Stripe;
import io.github.cdimascio.dotenv.Dotenv;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentCreateParams.ConfirmationMethod;

/**
 * Hello world!
 *
 */
public class Ideal
{
    public static void main(String[] args)
    {
        try {
            Dotenv dotenv = Dotenv.load();
            Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(1099L)
                .setCurrency("eur")
                .addPaymentMethodType("ideal")
                .putExtraParam("payment_method_data[ideal][bank]", "rabobank")
                .putExtraParam("payment_method_data[type]", "ideal")
                .setReturnUrl("http://m.com/finishedPayment")
                .setConfirmationMethod(ConfirmationMethod.MANUAL)
                .setConfirm(true)
                .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println(paymentIntent);
            System.out.println(paymentIntent.getNextAction().getRedirectToUrl().getUrl().toString());
            System.out.println("^^^ Follow the redirect then press any key");

            // The customer follows the redirect URL to their iDEAL bank!
            System.in.read();

            // Manually confirm the payment
            PaymentIntent confirmedPaymentIntent = paymentIntent.confirm();
            System.out.println(confirmedPaymentIntent);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
