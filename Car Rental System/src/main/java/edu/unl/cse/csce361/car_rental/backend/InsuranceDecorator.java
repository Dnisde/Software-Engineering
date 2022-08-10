package edu.unl.cse.csce361.car_rental.backend;

public class InsuranceDecorator implements PricedItem {

    PricedItem pricedItem;
    int costOfInsurance = 25;

    @Override
    public int getDailyRate() {
        return pricedItem.getDailyRate() + costOfInsurance;
    }

    @Override
    public String getLineItemSummary() {
        return pricedItem.getLineItemSummary() + "Insurance \n";
    }

    @Override
    public PricedItem getBasePricedItem() {
        return null;
    }
}
