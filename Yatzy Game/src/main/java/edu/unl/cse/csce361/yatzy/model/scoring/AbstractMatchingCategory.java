package edu.unl.cse.csce361.yatzy.model.scoring;

import edu.unl.cse.csce361.yatzy.Game;
import edu.unl.cse.csce361.yatzy.model.DieModel;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Intermediate class for scoring categories based on dice having the same values as other dice.
 */
public abstract class AbstractMatchingCategory extends AbstractDieBasedCategory {
    /**
     * Reports the face value of the dice that occurs at least as often as any other face value. In case of a tie, one
     * of the values will be nondeterministically selected.
     *
     * @param dice the dice to be considered
     * @return the most-common face value
     */
    protected static int mostCommonFace(List<DieModel> dice) {

        int size = Game.NUMBER_OF_DICE;
        int array[] = new int [size];
        int currCount = 1;
        int maxCount = 1;

        for(int i = 0; i < dice.size(); i++){
            array[i] = dice.get(i).getValue();
        }

        Arrays.sort(array);

        for(int i = 0; i < size - 1; i++){
            if(array[i] == array[i + 1]){
                currCount++;
            }
            else{
                if(currCount > maxCount){
                    maxCount = currCount;
                }
                currCount = 1;
            }
        }

        if(currCount > maxCount){
            maxCount = currCount;
        }

        return maxCount;
    }
}

