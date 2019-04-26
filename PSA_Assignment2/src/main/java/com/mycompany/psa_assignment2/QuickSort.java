/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.psa_assignment2;

import java.util.Arrays;

public class QuickSort<X extends Comparable<X>> implements Sort<X> {
    private final Helper<X> helper;

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public QuickSort(Helper<X> helper) {
        this.helper = helper;
    }

    public QuickSort() {
        this(new Helper<>("QuickSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        Arrays.sort(xs, from, to);
//        for (int i = from; i < to; i++)
//            for (int j = i; j > 0; j--)
//                if (less(xs[j], xs[j - 1]))
//                    swap(xs, j, j - 1);
//                else break;
//
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }
}
