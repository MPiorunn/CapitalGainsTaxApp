package com.capital.gains.tax.app.core.domain.billing;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class User {

    private UUID id;
    private UUID apiKeyId;
    private String name;
    private String address;
    private Billing billing;
    private double monthlyCost;


    void requestMade() {
        monthlyCost += billing.price;
    }

    enum Billing {
        TRIAL(0.0), STANDARD(1.1), PRO(2.2);

        double price;

        Billing(double price) {
        }

    }
}
