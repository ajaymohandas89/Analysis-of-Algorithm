package psa_randomwalk;

/**
 *
 * @author Ajay Mohandas
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RandomWalk {

    private int x = 0;
    private int y = 0;
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Steps,Mean_Distance";
    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        x+=dx;
        y+=dy;
        
    }
    /**
     * Perform a random walk of m steps
     *
     * @param n the number of steps the drunkard takes
     */
    public void randomWalk(int n) {
        // TO BE IMPLEMENTED ... (n random moves)
        // ... END IMPLEMENTATION
        for (int i =0; i< n;i++)
        {
            randomMove();
        }
    }
    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }
    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        //Euclidean formula for calculating distance
        double result = 0.0;
        result = Math.sqrt(Math.pow(x,2)+ Math.pow(y,2));
        return result;
    }
    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n){
        double totalDistance = 0;        
        
            for (int i = 0; i < n; i++) {
                RandomWalk walk = new RandomWalk();
                walk.randomWalk(m);
                totalDistance = totalDistance + walk.distance();
                            
            }
        
        
        return totalDistance / n;
    }
    //args is number of steps to be given by user
    public static void main(String[] args) {
        /*if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);*/
        String fileName = "./assignment1.csv";
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
        int m = 50;
        int n = 20;
        if (args.length > 1) n = Integer.parseInt(args[1]);
        for (int i = 0 ; i< 50;i++)
        {  
            
        double meanDistance = randomWalkMulti(m, n);
        
        System.out.println("Experiment"+(i+1)+ " "+m + " steps: " + meanDistance + " over " + n + " experiments");
        fileWriter.append(String.valueOf(m));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(meanDistance));
                fileWriter.append(NEW_LINE_SEPARATOR);   
                m+=50;
               
        }
}
        catch(Exception e)
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
    }

}
