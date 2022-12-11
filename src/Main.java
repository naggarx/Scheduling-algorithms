import java.util.*;

public class Main {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        System.out.println("which scheduler do you want");
        System.out.println("1 SJF");
        System.out.println("2 Round Robin");
        System.out.println("3 Priority Scheduling");
        System.out.println("4 AG Scheduling");
        Scheduler scheduler = null;
        int op = scan.nextInt();
        if (op == 1)
            scheduler = new Preemptive_shortest_job_first();
        else if (op == 2)
            scheduler = new Round_robin();
        else if(op ==3)
            scheduler = new Preemptive_priority_scheduling();
        else if (op == 4)
            scheduler = new AG_Scheduling();


        System.out.println("Enter the Number of Processes");
        int number = scan.nextInt();

        System.out.println("Enter the Time Quantum of Processes");
        int timeQuantum = scan.nextInt();

        System.out.println("Enter the Context switching time of Processes");
        int context = scan.nextInt();


        scheduler.input(number, timeQuantum, context);


        scheduler.doScheuling();
        scheduler.output();


    }
}
