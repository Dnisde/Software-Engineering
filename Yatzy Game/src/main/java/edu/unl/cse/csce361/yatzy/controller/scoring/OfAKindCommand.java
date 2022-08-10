package edu.unl.cse.csce361.yatzy.controller.scoring;

import edu.unl.cse.csce361.yatzy.model.scoring.OfAKindCategory;

/**
 * Scoring command for the OfAKind category.
 */
public class OfAKindCommand extends AbstractDieBasedScoringCommand {
    private final int targetCount;

    public OfAKindCommand(int targetCount) {
        super(new OfAKindCategory(targetCount));
        this.targetCount = targetCount;
    }

    @Override
    public String toString() {
        if(targetCount == 2){
            return "One Pair";
        }
        else if(targetCount == 3){
            return "Three of A Kind";
        }
        else if(targetCount == 4){
            return "Four of A Kind";
        }
        else{
            return "Yatzy";
        }
    }
}