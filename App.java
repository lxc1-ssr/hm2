import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Please enter the spending amount: ");
        double amountSpent = scanner.nextDouble();

        
        System.out.print("Please enter membership type (Silver, Gold, Platinum): ");
        String membershipType = scanner.next().toLowerCase();

        
        Membership membership = MembershipFactory.createMembership(membershipType);

       
        DiscountCalculator calculator = new DiscountCalculator(amountSpent, membership);

      
        double discountedPrice = calculator.calculateDiscountedPrice();

       
        if (discountedPrice >= 0) {
            System.out.printf("Total spending after deducting discounts: %.2f บาท\n", discountedPrice);
        } else {
            System.out.println("Spending amount cannot be used as a discount.");
        }
    }
}


class DiscountCalculator {
    private double amount;
    private Membership membership;

    public DiscountCalculator(double amount, Membership membership) {
        this.amount = amount;
        this.membership = membership;
    }

    public double calculateDiscountedPrice() {
        if (amount <= 1000) {
            return -1; 
        }
        double discount = amount * membership.getDiscountRate();
        return amount - discount;
    }
}


abstract class Membership {
    public abstract double getDiscountRate();
}


class SilverMembership extends Membership {
    @Override
    public double getDiscountRate() {
        return 0.05; 
    }
}


class GoldMembership extends Membership {
    @Override
    public double getDiscountRate() {
        return 0.10; 
    }
}


class PlatinumMembership extends Membership {
    @Override
    public double getDiscountRate() {
        return 0.15; 
    }
}


class MembershipFactory {
    public static Membership createMembership(String type) {
        switch (type) {
            case "silver":
                return new SilverMembership();
            case "gold":
                return new GoldMembership();
            case "platinum":
                return new PlatinumMembership();
            default:
                throw new IllegalArgumentException("Invalid membership type");
        }
    }
}
