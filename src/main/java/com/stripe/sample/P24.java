package com.stripe.sample;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import io.github.cdimascio.dotenv.Dotenv;
import com.stripe.model.Source;

public class P24 {
    public static void main(String[] args) {
        try {
            // Docs: https://site-admin.stripe.com/docs/sources/p24
            Dotenv dotenv = Dotenv.load();
            Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");

            // https://site-admin.stripe.com/docs/sources/p24#specifying-customer-bank
            String p24Bank = "plus_bank";

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("signature", "sig-xyz");
            Map<String, Object> redirect = new HashMap<>();
            redirect.put("return_url", "http://p24return.com");
            Map<String, Object> owner = new HashMap<>();
            owner.put("email", "test-p24@p24.com");
            Map<String, Object> params = new HashMap<>();
            params.put("amount", "1098");
            params.put("currency", "PLN");
            params.put("type", "p24");
            params.put("metadata", metadata);
            params.put("redirect", redirect);
            params.put("owner", owner);
            Map<String, Object> p24 = new HashMap<>();
            p24.put("bank", "ing");
            params.put("p24", p24);

            Source source = Source.create(params);

            System.out.println(source);
            System.out.println(source.getRedirect().getUrl());

            System.out.println("^^^ Follow the redirect then press any key");

            // The customer follows the redirect URL to their iDEAL bank!
            System.in.read();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
