package queueExercise;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExercise {
    public static void main(String[] args){
        Queue<String> queueCustomers = new LinkedList<>();
        queueCustomers.add("1234");
        queueCustomers.add("3412");
        queueCustomers.add("1125");

        while(!queueCustomers.isEmpty()){
            System.out.println("Customer " + queueCustomers.poll() + " is being helped now.");
        }
    }
}
