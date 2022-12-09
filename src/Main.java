import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        System.out.println("NEGO");
        Process p1 = new Process("p1",0,17,4,7);
        Process p2 = new Process("p2",2,6,7,9);
        Process p3 = new Process("p3",5,11,3,4);
        Process p4 = new Process("p4",15,4,6,6);
        AG_Scheduling ag = new AG_Scheduling();
        ag.AddProcesse(p1);
        ag.AddProcesse(p2);
        ag.AddProcesse(p3);
        ag.AddProcesse(p4);
        ag.schedule(1);
        Vector<String> st = ag.getOrder();
        for(String s : st)
            System.out.println(s);

    }
}
