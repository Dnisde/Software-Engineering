package edu.unl.cse.csce361.car_rental.backend;

public class SuvDecorator implements PricedItem {

    PricedItem pricedItem;
    int suvDailyRate = 500;

    @Override
    public int getDailyRate() {
        return pricedItem.getDailyRate() + suvDailyRate;
    }

    @Override
    public String getLineItemSummary() {
        return pricedItem.getLineItemSummary() + "SUV \n";
    }

    @Override
    public PricedItem getBasePricedItem() {
        return null;
    }
}
