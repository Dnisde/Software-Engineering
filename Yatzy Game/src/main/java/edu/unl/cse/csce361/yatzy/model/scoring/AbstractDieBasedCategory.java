package edu.unl.cse.csce361.yatzy.model.scoring;

import edu.unl.cse.csce361.yatzy.model.CategoryModel;
import edu.unl.cse.csce361.yatzy.model.DieModel;
import edu.unl.cse.csce361.yatzy.model.die.Die;

import java.util.List;

/**
 * Base class for scoring categories that make use of dice values to obtain their score.
 */
public abstract class AbstractDieBasedCategory implements CategoryModel {
    private int value = 0;
    private boolean valueIsFinal = false;

    @Override
    public int getHypotheticalScore(List<DieModel> dice) {
    	List<DieModel> satisfyData = getSatisfyingDice(dice);
        int sum = 0;
        for (int i = 0; i < satisfyData.size(); i++){
            sum += satisfyData.get(i).getValue();
        }
        return sum;
    }

    @Override
    public void assignScore(List<DieModel> dice) {
        value = getHypotheticalScore(dice);
        valueIsFinal = true;
    }

    @Override
    public int getScore() {
        return value;
    }

    @Override
    public boolean hasBeenScored() {
        return valueIsFinal;
    }

    @Override
    public void reset() {
        value = 0;
        valueIsFinal = false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + value;
    }

    protected abstract List<DieModel> getSatisfyingDice(List<DieModel> dice);
}


