import java.util.*;
public class Main {
    public static void main(String[] args)
    {

        Vector<Process> pro =new Vector<>();
        Process p1 = new Process("P1",0,17,4,7);
        Process p2 = new Process("P2",2,6,7,9);
        Process p3 = new Process("P3",5,11,3,4);
        Process p4 = new Process("P4",15,4,6,6);
        pro.add(p1);
        pro.add(p2);
        pro.add(p3);
        pro.add(p4);

        AG_Scheduling ag = new AG_Scheduling();
        ag.AddProcesse(p1);
        ag.AddProcesse(p2);
        ag.AddProcesse(p3);
        ag.AddProcesse(p4);
        ag.schedule();
        Vector<String> st = ag.getOrder();
        for(String s : st)
            System.out.println(s);

        Vector<Process> TimeHistory = ag.GetTimeHistroy();
        ag.getQuantumHistory(pro);
        double TotalATime = 0;
        double TotalWTime = 0;
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
