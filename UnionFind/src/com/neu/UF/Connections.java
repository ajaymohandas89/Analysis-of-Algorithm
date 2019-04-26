/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.UF;

public interface Connections {
    /**
     * Returns true if site p is connected to site q.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if there is a connection (direct or indirect) between p and q
     * @throws IllegalArgumentException if either p or q is an invalid site identifier
     */
    boolean isConnected(int p, int q);

    /**
     * Connects site p with site q
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException if either p or q is an invalid site identifier
     */
    void connect(int p, int q);
}