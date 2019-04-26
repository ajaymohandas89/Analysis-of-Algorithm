/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;
    private static ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) Arrays.sort(array, from, to);
        else {
            
            int mid = from + (to-1-from)/2;
            CompletableFuture<int[]> parsort1 = parsort(array,from,mid); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array,mid+1,to); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        int i=from;int j=mid+1;int k=0;
                        while(i<xs1.length && j<xs2.length){
                            if (xs1[i]<=xs2[j]){
                                result[k] = xs1[i];
                                k++;i++;
                            }
                            else{
                                result[k] = xs2[j];
                                k++;j++;
                            }
                        }
                        
                        while(i<xs1.length){
                            result[k] = xs1[i];
                                k++;i++;
                        }
                        
                        while(j<xs2.length){
                            result[k] = xs1[j];
                                k++;j++;
                        }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO IMPLEMENT
                    System.arraycopy(array, from, result, 0, result.length);
                    sort(result, 0, to - from);
                    return result;
                }
        ,forkJoinPool);
    }
}