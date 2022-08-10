package edu.unl.cse.csce361.car_rental.backend;

public class VanDecorator implements PricedItem {

    PricedItem pricedItem;
    int vanDailyRate = 100;


    @Override
    public int getDailyRate() {
        return pricedItem.getDailyRate() + vanDailyRate;
    }

    @Override
    public String getLineItemSummary() {
        return pricedItem.getLineItemSummary() + "Van \n";
    }

    @Override
    public PricedItem getBasePricedItem() {
        return null;
    }
}