package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyr) {

        Pyramid pyramid = validate(pyr);

        // Return zero for empty pyramids
        if(pyramid.getRows() == 0){
            return 0;
        }

        int      rowCount = pyramid.getRows();
        long[][] table   = new long[rowCount][rowCount];

        // Fill the first rows of the table with the values of the topmost row of the pyramid
        for (int i = 0 ; i < rowCount;i ++){
            table[0][i] = pyramid.get(0,i);
        }

        // Fill the remaining rows with the rule:
        // table(i,j) = max(table(i-1,j) + table(i,j) ,table(i-1,j+1) + table(i,j))
        for (int i = 1; i < rowCount; i++){
            for (int  j = 0 ; j<rowCount - i ;j++){
                long left = table[i-1][j] + pyramid.get(i,j);
                long right = table[i-1][j + 1] + pyramid.get(i,j);
                table[i][j] = Math.max(left,right);
            }
        }

        // The head of the inverted pyramid stores the maximum sum
        return table[rowCount - 1][0];
    }

    /**
     * Validates the given {@code pyramid} and re
     * @param pyramid the pyramid object
     * @throws IllegalArgumentException if the {@code pyramid} is {@code null}
     * @return the {@code pyramid} object itself
     */
    private Pyramid validate(Pyramid pyramid) throws IllegalArgumentException{
        if(pyramid == null){
            throw new IllegalArgumentException();
        }
        return pyramid;
    }
    
}
