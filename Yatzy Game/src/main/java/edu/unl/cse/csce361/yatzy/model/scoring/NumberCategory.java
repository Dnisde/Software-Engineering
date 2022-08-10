package edu.unl.cse.csce361.yatzy.model.scoring;

import edu.unl.cse.csce361.yatzy.model.die.Die;
import edu.unl.cse.csce361.yatzy.model.DieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A scoring category whose value is the sum of the die faces that hold a particular value.
 */
public class NumberCategory extends AbstractDieBasedCategory {
    private final int targetNumber;

    public NumberCategory(int targetNumber) {
        if ((1 <= targetNumber) && (targetNumber <= Die.getNumberOfSides())) {
            this.targetNumber = targetNumber;
        } else {
            throw new IllegalArgumentException("Target number must be a face on the " + Die.getNumberOfSides() +
                    "-sided dice.");
        }
    }

    /**
     * Obtains the face value used to find the satisfying dice.
     *
     * @return The targeted face value
     */
    public int getTargetNumber() {
        return targetNumber;
    }

    @Override
    public boolean isSatisfiedBy(List<DieModel> dice) {
        for(int i = 0; i < dice.size(); i++){
            if (dice.get(i).getValue() == getTargetNumber()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<DieModel> getSatisfyingDice(List<DieModel> dice) {
        List<DieModel> satisfiedDice = new ArrayList<>();
        if(isSatisfiedBy(dice)){
            for(int i = 0; i < dice.size(); i++){
                if(dice.get(i).getValue() == getTargetNumber()){
                    satisfiedDice.add(dice.get(i));
                }
            }
        }
        return satisfiedDice;
    }
}
