/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.psa_assignment2;

public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for SelectionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public SelectionSort(Helper<X> helper) {
        this.helper = helper;
    }

    public SelectionSort() {
        this(new Helper<>("SelectionSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        for (int i = from;i< (to - 1);i++)
        {
            X min = xs[i];
            for (int j = i+1; j<to ; j++)
            {
                if(xs[j].compareTo(min)>0)
                {
                    xs[i] = xs[j];
                    xs[j] = min;
                }
            }
        }
            
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}
