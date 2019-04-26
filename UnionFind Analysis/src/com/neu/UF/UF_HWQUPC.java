/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.UF;

import java.util.Arrays;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Height-weighted Quick Union with Path Compression
 */
public class UF_HWQUPC implements UF {
    /**
     * Ensure that site p is connected to site q,
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     */
    public void connect(int p, int q) {
        if (!isConnected(p, q)) union(p, q);
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param  n the number of sites
     * @param pathCompression whether to use path compression
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_HWQUPC(int n, boolean pathCompression) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        this.pathCompression = pathCompression;
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     * This data structure uses path compression
     *
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_HWQUPC(int n) {
        this(n, true);
    }

    public void show() {
        for (int i = 0; i < parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], height[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int components() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        if(pathCompression == true)
           doPathCompression(p);
        while (root!=parent[root])
		{
                    root = parent[root];
		}

        return root;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        // CONSIDER can we avoid doing find again?
        mergeComponents(find(p), find(q));
        count--;
    }

    /**
     * Used only by testing code
     * @param pathCompression true if you want path compression
     */
    public void setPathCompression(boolean pathCompression) {
        this.pathCompression = pathCompression;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UF_HWQUPC:");
        stringBuilder.append("\n  count: ").append(count);
        stringBuilder.append("\n  path compression? ").append(pathCompression);
        stringBuilder.append("\n  parents: ").append(Arrays.toString(parent));
        stringBuilder.append("\n  heights: ").append(Arrays.toString(height));
        return stringBuilder.toString();
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    private void updateParent(int p, int x) {
        parent[p] = x;
    }

    private void updateHeight(int p, int x) {
        height[p] += height[x];
    }

    /**
     * Used only by testing code
     * @param i the component
     * @return the parent of the component
     */
    private int getParent(int i) {
        return parent[i];
    }

    private final int[] parent;   // parent[i] = parent of i
    private final int[] height;   // height[i] = height of subtree rooted at i
    private int count;  // number of components
    private boolean pathCompression = false;
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Number of Sites, Number of Connections";
    private void mergeComponents(int i, int j) {
        // TO BE IMPLEMENTED ... make shorter root point to taller one
        if (i==j) return;
        if (height[i] < height[j])
        {
            //doPathCompression(i,j);
            updateParent(i,j);
            updateHeight(j,i);
            /*parent[i] = j;
            height[j]+=height[i];*/
        }
        else{
            updateParent(j,i);
            updateHeight(i,j);
            /*parent[j] = i;
            height[i]+=height[j];*/
            
            //doPathCompression(i);
        }
        //doPathCompression(count);
        
        //throw new RuntimeException("not implemented");
        // ... END IMPLEMENTATION
    }

    /**
     * This implements the single-pass path-halving mechanism of path compression
     */
    private void doPathCompression(int i) {
        // TO BE IMPLEMENTED ... update parent to value of grandparent
        
        
            while(i != parent[i]){
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
        
        
        
        //throw new RuntimeException("not implemented");
        // ... END IMPLEMENTATION
    }
    
    public static void main(String[] args){
        FileWriter fileWriter = null;
        Random rand = new Random();
        String fileName = "./UnionFind.csv";
        
        try{
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (int n=100;n<=500000;n=n*2){
                UF_HWQUPC uf = new UF_HWQUPC(n);
                int i=0;
                while(uf.components() > 1)
                {
                    int p = rand.nextInt(n);
                    int q = rand.nextInt(n);
                    if (uf.isConnected(p, q))
                        ;
                    else{
                        uf.connect(p,q);
                    //uf.show();
                    
                    }
			i++;
                }
                int temp = i;
                fileWriter.append(String.valueOf(n));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(temp));
                fileWriter.append(NEW_LINE_SEPARATOR); 
            }
        }catch(Exception e)
            {
                System.out.println("Error in CSV file");
                e.printStackTrace();
            }
        try {
                fileWriter.flush();
                fileWriter.close();
            } 
        catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        
	
        System.out.println("UnionFind successfully run. Please check the the UnionFind.xlsx file for analysis");
		

    }
}