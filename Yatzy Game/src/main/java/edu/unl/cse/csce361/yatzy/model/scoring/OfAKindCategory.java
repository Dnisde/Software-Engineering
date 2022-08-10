package edu.unl.cse.csce361.yatzy.model.scoring;

import edu.unl.cse.csce361.yatzy.Game;
import edu.unl.cse.csce361.yatzy.model.DieModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap; /* For searching the repeat value existed in List<DidModel> dice */
import java.util.Map;
/**
 * Scoring category for dice whose face value is the same as other dice. The constructor's argument specifies how many
 * dice must match each other. Typical usage would be one pair, three of a kind, four of a kind, and Yatzy.
 */
public class OfAKindCategory extends AbstractMatchingCategory {
    private final int targetCount;
    /* Create a HashMap to store the repeated Dice Face category */
    private HashMap<Integer,Integer> dicemap = new HashMap<Integer, Integer>();
    
    public OfAKindCategory(int targetCount) {
        if ((1 < targetCount) && (targetCount <= Game.NUMBER_OF_DICE)) {
            this.targetCount = targetCount;
        } else if (targetCount > Game.NUMBER_OF_DICE) {
            throw new IllegalArgumentException("Cannot have " + targetCount + " of a kind with only " +
                    Game.NUMBER_OF_DICE + " dice.");
        } else if (targetCount == 1) {
            throw new IllegalArgumentException("Trivial one of a kind has no defined score.");
        } else {
            throw new IllegalArgumentException("Vacuous " + targetCount + " of a kind has no defined score.");
        }
    }

    /**
     * Reports whether at least a specified number of dice (<code>minmumSizeOfGroup</code>) all have the same face
     * value.
     *
     * @param dice               the collection of dice to be evaluated
     * @param minimumSizeOfGroup the minimum number of dice that must have the same value
     * @return <code>true</code> if there are at least the specified number of dice with the same value,
     * <code>false</code> otherwise
     */
    boolean isSimpleMatch(List<DieModel> dice, int minimumSizeOfGroup) {
    	return mostCommonFace(dice) >= minimumSizeOfGroup;
	}

    @Override
    public boolean isSatisfiedBy(List<DieModel> dice) {
    	/* This function will detect repeated dice within List<DieModel> dice, and store it into map */

    	if (mostCommonFace(dice) >= targetCount) {
    		return true;
    	}else {
    		return false;
    	}
    }

    @Override
    public int getHypotheticalScore(List<DieModel> dice) {
    	
    	HashMap<Integer,Integer> dicemap2 = new HashMap<Integer, Integer>();
    	
    	for(int i=0;i<Game.NUMBER_OF_DICE;i++) {
    		if(dicemap2.containsKey(dice.get(i).getValue())) { // if the map has already contain the key - (Dice Face), we add the count.
    			int count = dicemap2.get(dice.get(i).getValue());
    			dicemap2.put(dice.get(i).getValue(), count + 1);
    		}else {
    			dicemap2.put(dice.get(i).getValue(), 1); // As default, each appeared (Dice Face) will be push as appears one time in map at initial
    		}
    	}
    	/* For debug */
    	/* System.out.println(dicemap2);
    	 * 
    	 */
    	
    	/* Updating the HashMap to global*/
    	dicemap = dicemap2;
    	
    	if(dicemap.size() == 1 && targetCount == 5) {
//    		System.out.println(" You got Yatzy!"); /* For Debug */
	    	int valueofpoints = 50;
	    	return valueofpoints;
    	}
	    int valueOfpoints = super.getHypotheticalScore(dice);
	    return valueOfpoints;
    }

    
    @Override
    protected List<DieModel> getSatisfyingDice(List<DieModel> dice) {
    	List<DieModel> satisfiedDice = new ArrayList<>();
    	if(isSimpleMatch(dice, targetCount)) {/* if repeated Dice matches all condition */
    		for(Integer i : dicemap.keySet()) {
        		/* Search the number of same Dice Face category */
        		if(dicemap.get(i)>1) { 
        			int Diceface = i; // Get the Dice Face which is repeated from value in hashmap
        			for(int j = 0; j < Game.NUMBER_OF_DICE; j++){
        				/* Add all the dices which match the category into List<DieModel> dice */
        				if(dice.get(j).getValue() == Diceface) {
        					satisfiedDice.add(dice.get(j));
        				}
        			}
        		}
        	}
    	}
    	
        return satisfiedDice;
    }
    
}
