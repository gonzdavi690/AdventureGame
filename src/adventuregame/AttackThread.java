package adventuregame;

import java.util.Scanner;

/* This program shows how to use a thread to detect something happening in 6 seconds */
public class AttackThread {

    static Scanner sc = new Scanner(System.in);
    static boolean attack = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome. Press <ENTER> to continue.");
        sc.nextLine();
        new Thread6Sec().start();

        //DO NOT USE SCANNER FOR THE NEXT n seconds or the program will crash due to the thread trying to use Scanner too.
        System.out.println("the bear attacks you");
        Thread.sleep(2000);
        if (!attack) {
            System.out.println("the bear eats you");
            Thread.sleep(2000);
        }
        if (!attack) {            
            System.out.println("the bear is happy");
            Thread.sleep(1000);
        }
        if (attack) { //this has to be done in the first n seconds
            System.out.println("No way, you were just playing dead. You awake and kill the bear!");
        } else {
            System.out.println("Your skeleton looks very attractive.");
        }

    }

    private static class Thread6Sec extends Thread {

        private static int time = 5;
        public void run() {
            System.out.println("You have " + time + " seconds to type 'ATTACK'");
            while(time-- > 0) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {}
                //This should print out a "*" every second, but it doesn't because the scanner blocks when there is no input
                
                //I wonder if Bufferedreader also blocks?
                System.out.println("*");
                
                String s2 = "";
                if (sc.hasNext()) s2 = sc.next().toUpperCase();
                if (s2.equals("ATTACK")) attack = true;
            }
            System.out.println(time +" seconds are up");
        }
    }

}