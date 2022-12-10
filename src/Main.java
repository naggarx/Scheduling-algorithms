import java.util.*;
public class Main {
    public static void main(String[] args) {
        String name;
        double arrival;
        double Burst;
        int prio;
        double Quanto;

        Scheduler scheduler;
        Vector<Process> pro = new Vector<>();
        AG_Scheduling AG = new AG_Scheduling();
        Vector<String> st = AG.getOrder();

        Vector<Process> TimeHistory = AG.GetTimeHistroy();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Number of Processes");

        int number = scan.nextInt();
        for (int i = 0; i < number; i++)
        {
            System.out.println("Enter the Name of Process : ");
            System.out.println();
            name= scan.next();
            System.out.println("Enter the Arrival Time of Process : ");
            arrival= scan.nextInt();
            System.out.println("Enter the Burst Time of Process : ");
            Burst= scan.nextInt();
            System.out.println("Enter the Priority of Process : ");
            prio= scan.nextInt();
            System.out.println("Enter the Quantum Time of Process : ");
            Quanto= scan.nextInt();
            Process p1 = new Process(name,arrival,Burst,prio,Quanto);
            pro.add(p1);
            AG.AddProcesse(p1);

        }

        AG.schedule();
        System.out.println("Processes execution order : ");
        for(String s : st)
        { System.out.print(s+" , ");}
        System.out.println();
        AG.getQuantumHistory(pro);
        double TotalATime = 0;
        double TotalWTime = 0;
        System.out.println("Turnaround Time and Waiting Time for each process : ");
        for(Process P: TimeHistory)
        {

            P.setWaitingTime((P.getTurnARoundTime() - P.getProcessTime()));
            System.out.println(P.getName() + " Turn Around Time: " + P.getTurnARoundTime() + " Waiting Time: " + P.getWaitingTime());
            TotalATime+=P.getTurnARoundTime();
            TotalWTime+=P.getWaitingTime();
        }
        System.out.println("Average Total Time : " + TotalATime/TimeHistory.size());
        System.out.println("Average Waiting Time : " + TotalWTime/TimeHistory.size());


    }
}
