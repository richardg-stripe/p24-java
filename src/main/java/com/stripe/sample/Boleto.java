package com.stripe.sample;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Hello world!
 *
 */
public class Boleto {

	public static void main(String[] args) {
		try {
			// Docs: https://stripe.com/docs/payments/boleto
			Dotenv dotenv = Dotenv.load();
			Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

			// Stripe.API_VERSION = "2019-05-16;boleto_pilot_beta=v1";


			// "Stripe-Version: 2020-08-27; boleto_pilot_beta=v1"
			PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
					.setAmount(1099L)
					.setCurrency("brl")
					.addPaymentMethodType("boleto_pilot")
					.putExtraParam("payment_method_data[boleto_pilot][tax_id]", "123123")
					.putExtraParam("payment_method_data[type]", "boleto_pilot")
					.putExtraParam("payment_method_data[billing_details][name]", "Richard G")
					.putExtraParam("payment_method_data[billing_details][email]", "somethingsucceed_immediately@something.com")
					.putExtraParam("payment_method_data[billing_details][address][line1]", "Av. Rio Branco")
					.putExtraParam("payment_method_data[billing_details][address][city]", "Rio de Janeiro")
					.putExtraParam("payment_method_data[billing_details][address][state]", "RJ")
					.putExtraParam("payment_method_data[billing_details][address][postal_code]", "20040-009")
					.putExtraParam("payment_method_data[billing_details][address][country]", "BR")
					.setReturnUrl("http://m.com/finishedPayment")
					.setConfirm(true)
					.build();
			PaymentIntent paymentIntent = PaymentIntent.create(params);

			// Person cashes the Boleto Voucher offline...


		} catch (Exception exception) {
			System.out.println(exception);
		}
	}
}
