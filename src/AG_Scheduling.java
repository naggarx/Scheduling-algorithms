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
    public boolean Compare(Process p1 ,Process p2) {
        if(p1.getName()==p2.getName())
        {return true;}
        else
        {return false;}

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
    public int getindex(Process p1, Vector<Process> P)
    {
        int indx=-1;
        for(int j=0;j<P.size();j++)
        {
            if(P.get(j).getName()==p1.getName())
            {
                indx=j;
            }
        }
        return indx;

    }
    public boolean checkBurstTime(Process p)
    {
        if(p.getBurstTime()<=0)
        {
            return true;
        }
        else {
            return false;
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

    public void schedule(int n) {
        double Time=0,quanto=0,q1=0,q2=0,counter=0,burstT=0,prio=0;
        int indx=0;

        Process p1;

            Addarrival(processes,Time);
            p1=queue.get(0);
            setOrder(p1.getName());
            quanto=p1.getQuantumTime();
            burstT=p1.getBurstTime();
            q1=ceil(quanto/4);
            indx=getindex(p1,processes);
        processes.get(indx).setBurstTime((int)(burstT-q1));
            if(checkBurstTime(p1))
            {
                p1.setBurstTime(0);
                processes.remove(p1);
                queue.remove(p1);
                Time+=p1.getBurstTime();
            }
            Time+=q1;
            Addarrival(processes,Time);

            Process p2=FindHighPri(processes);
            if(Compare(p1,p2) & !checkBurstTime(p1))
            {
                q2=ceil(q1/4);
                Time+= q2;
                processes.get(indx).setBurstTime((int)(burstT-q2));
                if(checkBurstTime(p1))
                {
                    p1.setBurstTime(0);
                    processes.remove(p1);
                    queue.remove(p1);
                }
                Addarrival(processes,Time);
                p2=FindLeastBrust(processes);
                if(Compare(p1,p2) & !checkBurstTime(p1))
                {
                    quanto=quanto-q1-q2;
                    processes.get(indx).setBurstTime((int)(burstT-quanto));
                    if(checkBurstTime(p1))
                    {
                        p1.setBurstTime(0);
                        processes.remove(p1);
                        queue.remove(p1);
                    }


                }
                else
                {

                }


            }
            else
            {

            }


    }
}
