package edu.unl.cse.csce361.yatzy.model.scoring;

import edu.unl.cse.csce361.yatzy.Game;
import edu.unl.cse.csce361.yatzy.model.DieModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap; /* For searching the repeat value existed in List<DidModel> dice */

/**
 * Scoring category for dice, divided into two groups, such that each group consists of dice whose face value is the
 * same as the other dice in that group. The face value of the dice in one group <i>must</i> be distinct from the face
 * value of the dice in the other group. The constructor's arguments specify how many dice must match each other in
 * the two groups. Typical usage would be two pair and full house.
 */
public class OfTwoKindsCategory extends AbstractMatchingCategory {
    private final int firstTargetCount;
    private final int secondTargetCount;
    
//    /* Create a HashMap to store the repeated Dice Face category */
//    private HashMap<Integer,Integer> diceMap = new HashMap<Integer, Integer>();

    public OfTwoKindsCategory(int firstTargetCount, int secondTargetCount) {
        int targetCountSum = firstTargetCount + secondTargetCount;
        if ((firstTargetCount > 1) && (secondTargetCount > 1) && (targetCountSum <= Game.NUMBER_OF_DICE)) {
            this.firstTargetCount = Math.max(firstTargetCount, secondTargetCount);
            this.secondTargetCount = Math.min(firstTargetCount, secondTargetCount);
        } else if (targetCountSum > Game.NUMBER_OF_DICE) {
            throw new IllegalArgumentException("Cannot have " + firstTargetCount + " of one kind and " +
                    secondTargetCount + " of another kind with only " + Game.NUMBER_OF_DICE + " dice.");
        } else if ((firstTargetCount == 0) || (secondTargetCount == 0)) {
            throw new IllegalArgumentException("With one of the target counts being zero of a kind, use " +
                    "OfAKindCategory instead of OfTwoKindsCategory.");
        } else if ((firstTargetCount == 1) || (secondTargetCount == 1)) {
            throw new IllegalArgumentException("With one of the target counts being one of a kind, use " +
                    "OfAKindCategory instead of OfTwoKindsCategory.");
        } else {
            throw new IllegalArgumentException("Unexpected argument combination.");
        }
    }

    /**
     * Reports whether there are (at least) two groups of the specified numbers of dice, each of which consists of dice
     * that all have the same face value. While the first group is understood to be the larger of the two groups, the
     * two groups may be of equal size.
     *
     * @param dice                     the collection of dice to be evaluated
     * @param minimumSizeOfFirstGroup  the minimum number of dice in the larger group of dice with the same value
     * @param minimumSizeOfSecondGroup the minimum number of dice in the smaller group of dice with the same value
     * @return <code>true</code> if there are two requisite-sized groups of common-valued dice, <code>false</code>
     * otherwise
     */
    boolean isCompoundMatch(List<DieModel> dice, int minimumSizeOfFirstGroup, int minimumSizeOfSecondGroup) {
    	
    	HashMap<Integer,Integer> dicemap = new HashMap<Integer, Integer>();
    	
    	for(int i=0;i<Game.NUMBER_OF_DICE;i++) {
    		if(dicemap.containsKey(dice.get(i).getValue())) { // if the map has already contain the key - (Dice Face), we add the count.
    			int count = dicemap.get(dice.get(i).getValue());
    			dicemap.put(dice.get(i).getValue(), count + 1);
    		}else {
    			dicemap.put(dice.get(i).getValue(), 1); // As default, each appeared (Dice Face) will be push as appears one time in map at initial
    		}
    	}
    	
    	/* For debug */
    	/* System.out.println(map);
    	 * 
    	 */
    	
    	if (mostCommonFace(dice) >= 2) {
    		
    	/* the case if it detect same Dice Face with two pairs */
	    	if(minimumSizeOfFirstGroup >= 2 && minimumSizeOfSecondGroup >= 2) {
	    			
	    		/* if the two group size are not equal */
	    		int[] dicelist = new int[2];
	   			int j = 0;
	   			for (Integer i : dicemap.keySet()) {
	   				if(dicemap.get(i)>1) { 
	   					dicelist[j] = dicemap.get(i);  // store two different number of group that has the same Dice Face
	    				j = j + 1;
    				}
   				}
	   			int firstsize = Math.max(dicelist[0],dicelist[1]);
	  			int secondsize = Math.min(dicelist[0],dicelist[1]);
	   			if(firstsize >= minimumSizeOfFirstGroup && secondsize >= minimumSizeOfSecondGroup) {
	   				dicemap.clear();
	  				return true;
	    			
	    		}
	   		}
    	}
   			
    	dicemap.clear();
        return false;
    }

    @Override
    public boolean isSatisfiedBy(List<DieModel> dice) {
    	
    	HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
    	/* This function will detect repeated dice within List<DieModel> dice, and store it into map */
    	for(int i=0;i<Game.NUMBER_OF_DICE;i++) {
    		if(map.containsKey(dice.get(i).getValue())) { // if the map has already contain the key - (Dice Face), we add the count.
    			int count = map.get(dice.get(i).getValue());
    			map.put(dice.get(i).getValue(), count + 1);
    		}else {
    			map.put(dice.get(i).getValue(), 1); // As default, each appeared (Dice Face) will be push as appears one time in map at initial
    		}
    	}
    	/* For debug */
    	/* System.out.println(map);
    	 * 
    	 */
    	
    	if (mostCommonFace(dice) >= 2) {
    		/* If there exists a category of dice who has the same face */
    		
    		if(firstTargetCount >= 2 && secondTargetCount >= 2) {
    		/* the case if it detect same Dice Face with two pairs */
    			int[] dicelist = new int[2];
   				int j = 0;
   				for (Integer i : map.keySet()) {
   					if(map.get(i)>1) { 
	    				dicelist[j] = map.get(i);  // store two different number of group that has the same Dice Face
	    				j = j + 1;
    				}
   				}
   				int firstsize = Math.max(dicelist[0],dicelist[1]);
   				int secondsize = Math.min(dicelist[0],dicelist[1]);
   				if(firstsize >= firstTargetCount && secondsize >= secondTargetCount) {
   					map.clear();
  					return true;
   				}
    		}
    		
    	}
    	map.clear();
        return false;
    }

    @Override
    protected List<DieModel> getSatisfyingDice(List<DieModel> dice) {
    /* This function will detect repeated dice within List<DieModel> dice, and store it into map */
    	HashMap<Integer,Integer> diceMap = new HashMap<Integer, Integer>();
    	List<DieModel> satisfiedDice = new ArrayList<>();
    	
    	for(int i=0;i<Game.NUMBER_OF_DICE;i++) {
    		if(diceMap.containsKey(dice.get(i).getValue())) { // if the map has already contain the key - (Dice Face), we add the count.
    			int count = diceMap.get(dice.get(i).getValue());
    			diceMap.put(dice.get(i).getValue(), count + 1);
    		}else {
    			diceMap.put(dice.get(i).getValue(), 1); // As default, each appeared (Dice Face) will be push as appears one time in map at initial
    		}
    	}
    	
    	if(isSatisfiedBy(dice) == true) {
    		int[] dicelist = new int[2];
			int j = 0;
    		for(Integer i: diceMap.keySet()) {
    			if(diceMap.get(i)>1) { 
					dicelist[j] = i;  // store two kind of group that has the same Dice Face into a list
					j = j + 1;
				}
        	}
    		/* For debug */
        	System.out.println(diceMap);
        	System.out.println(dicelist[0]);
        	System.out.println(dicelist[1]);
        	
    		for(int i = 0; i < Game.NUMBER_OF_DICE; i++){
				/* Add all the dices which match the category into List<DieModel> dice */
				if(dice.get(i).getValue() == dicelist[0]) {
					satisfiedDice.add(dice.get(i));
				}else if(dice.get(i).getValue() == dicelist[1]) {
					satisfiedDice.add(dice.get(i));
				}
			}
    	}
    	diceMap.clear();
        return satisfiedDice;
    }
}
