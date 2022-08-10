package edu.unl.cse.csce361.yatzy.controller.scoring;

import edu.unl.cse.csce361.yatzy.model.scoring.OfTwoKindsCategory;

/**
 * Scoring command for the OfTwoKind category.
 */
public class OfTwoKindsCommand extends AbstractDieBasedScoringCommand {
    private final int firstTargetCount;
    private final int secondTargetCount;

    public OfTwoKindsCommand(int firstTargetCount, int secondTargetCount) {
        super(new OfTwoKindsCategory(firstTargetCount, secondTargetCount));
        this.firstTargetCount = firstTargetCount;
        this.secondTargetCount = secondTargetCount;
    }

    @Override
    public String toString() {
        if(firstTargetCount == 2 && secondTargetCount == 2){
            return "Two Pair";
        }
        else if(firstTargetCount == 3 && secondTargetCount == 2){
            return "Full House";
        }
        else{
            return null;
        }
    }
}