import java.util.*;

import static java.lang.Math.ceil;

public class AG_Scheduling
{
    Vector<String> Order= new Vector<>();
    Vector<Process> queue= new Vector<>();

    Vector<Process> processes = new Vector<>();
    public Process FindHighPri(Vector<Process> processes)
    {
        Process High = new Process();
        High.setPriority(100000000);
        for (Process process : processes)
        {
            if (process.getPriority() < High.getPriority())
                High = process;
        }
        return High;
    }
    public Process FindLeastBrust(Vector<Process> processes)
    {
        Process Lowest = new Process();
        Lowest.setBurstTime(100000000);
        for (Process process : processes)
        {
            if (process.getBurstTime() < Lowest.getBurstTime())
                Lowest = process;
        }
        return Lowest;
    }

    public void setOrder(String order) {
        Order.add(order);
    }

    public Vector<String> getOrder() {
        return Order;
    }

    public void AddProcesse(Process p1)
    {
        this.processes.add(p1);
    }
    public void UpdateHistory(Vector<Process> p1)
    {
        for(int i=0;i<p1.size();i++)
        {
            System.out.println(p1.get(i)+",");
        }

    }
    public void Addarrival(Vector<Process> P,double t)
    {
        for(int i=0;i<P.size();i++)
        {
            if (P.get(i).getArrivalTime() <= t)
            {
                this.queue.add(P.get(i));

            }
        }

    }

//fcfs---priority---shortestburst//

    public void schedule(Vector<Process> p1,int n) {
        double Time=0,quanto=0,q1=0,q2=0,counter=0,burstT=0,prio=0;
        int indx=0;
            Addarrival(p1,Time);
            setOrder(queue.get(0).getName());
            quanto=queue.get(0).getQuantumTime();
            burstT=queue.get(0).getBurstTime();
            q1=ceil(quanto/4);
            for(int j=0;j<p1.size();j++)
            {
                if(p1.get(j).getName()==queue.get(0).getName())
                {
                    indx=j;
                }
            }
            p1.get(indx).setBurstTime((int)(burstT-q1));
            Time+=q1;
            Addarrival(p1,Time);


    }
}
