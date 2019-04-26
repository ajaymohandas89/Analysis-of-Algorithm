/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.psa_assignment2;

import java.util.Arrays;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        this.helper = helper;
    }

    public InsertionSort() {
        this(new Helper<>("InsertionSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        Arrays.sort(xs, from, to);
        /*for (int i = from + 1; i<to;i++)
        {   
            X key = xs[i];
            int j = i-1;
          
            while (j>=0 && xs[j].compareTo(key)>0)
            {
                xs[j+1] = xs[j];
                xs[j] = key;
                j = j-1;    
            }
        }*/
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}
