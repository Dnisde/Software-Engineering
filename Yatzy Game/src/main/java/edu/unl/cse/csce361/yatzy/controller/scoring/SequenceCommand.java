package edu.unl.cse.csce361.yatzy.controller.scoring;

import edu.unl.cse.csce361.yatzy.model.scoring.SequenceCategory;

/**
 * Scoring command for the Sequence category.
 */
public class SequenceCommand extends AbstractDieBasedScoringCommand {
    private final int start;

    public SequenceCommand(int start) {
        super(new SequenceCategory(start));
        this.start = start;
    }

    @Override
    public String toString() {
        if(start == 1){
            return "Small Straight";
        }
        else{
            return "Large Straight";
        }
    }
}