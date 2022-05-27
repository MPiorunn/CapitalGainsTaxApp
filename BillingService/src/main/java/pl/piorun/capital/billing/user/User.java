package pl.piorun.capital.billing.user;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private UUID id;
    private UUID apiKeyId;
    private String username;
    private Billing billing;
    private double monthlyCost;

    public void requestMade() {
        monthlyCost += billing.getPrice();
    }

    public void addApiKey(UUID id) {
        this.apiKeyId = id;
    }

    public enum Billing {
        TRIAL(0.0), STANDARD(1.1), PRO(2.2);

        double price;

        public double getPrice() {
            return price;
        }

        Billing(double price) {
        }
    }
}
