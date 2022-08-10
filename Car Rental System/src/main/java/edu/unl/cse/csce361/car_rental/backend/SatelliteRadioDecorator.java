package edu.unl.cse.csce361.car_rental.backend;

public class SatelliteRadioDecorator implements PricedItem {

    PricedItem pricedItem;
    int costOfRadio = 25;

    @Override
    public int getDailyRate() {
        return pricedItem.getDailyRate() + costOfRadio;
    }

    @Override
    public String getLineItemSummary() {
        return pricedItem.getLineItemSummary() + "SatelliteRadio \n";
    }

    @Override
    public PricedItem getBasePricedItem() {
        return null;
    }
}