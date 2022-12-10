import java.util.*;

import static java.lang.Math.ceil;

public class AG_Scheduling extends Scheduler {
    Vector<Process> queue = new Vector<>();



    public Process FindHighPri(Vector<Process> processes) {
        Process High = new Process();
        High.setPriority(100000000);
        for (Process process : processes) {
            if (process.getPriority() < High.getPriority())
                High = process;
        }
        return High;
    }

    public Process FindLeastBrust(Vector<Process> processes) {
        Process Lowest = new Process();
        Lowest.setBurstTime(100000000);
        for (Process process : processes) {
            if (process.getBurstTime() < Lowest.getBurstTime())
                Lowest = process;
        }
        return Lowest;
    }

    public void setOrder(String order) {
        Order.add(order);
    }

    public boolean Compare(Process p1, Process p2) {
        return p1.getName() == p2.getName();

    }



    public void UpdateHistory(Vector<Process> p1) {
        for (Process process : p1) {
            System.out.println(process + ",");
        }

    }
    public void getQuantumHistory(Vector<Process> p1) {
        for (Process process : p1) {
           Vector<Double> v=process.getQuantumHistory();
            System.out.println("Process " +process.getName() +" History: ");
            System.out.println(v);

        }

    }


    public int getindex(Process p1, Vector<Process> P) {
        int indx = -1;
        for (int j = 0; j < P.size(); j++) {
            if (P.get(j).getName() == p1.getName()) {
                indx = j;
            }
        }
        return indx;

    }



    public void Addarrival(Vector<Process> P, double t1 ,double t2)
    {
        for (Process process : P)
        {
            if (process.getArrivalTime() <= t1 && process.getArrivalTime()>t2)
            {
                //System.out.println("ana fl arival" + process.getArrivalTime() + " "+ process.getName());
                this.queue.add(process);

            }
        }

    }


//fcfs---priority---shortestburst//

    public void schedule()
    {
        double Time = 0, quanto = 0, q1 = 0, q2 = 0, q3 = 0, counter = 0, burstT = 0, prio = 0,LastT;
        int indx = 0;
        int indx2 ;
        Process p1;
        Addarrival(processes, Time,-1);
        for (Process process : processes)
        {
            process.AddQuantumHistory(process.getQuantumTime());
        }

        while(queue.size() == 0)
        {
            LastT=Time;
            Time++;
            Addarrival(processes, Time,LastT);
        }
        p1 = queue.get(0);

        while (queue.size() > 0)
        {

            setOrder(p1.getName());
            quanto = p1.getQuantumTime();
            burstT = p1.getBurstTime();
            q1 = ceil(quanto / 4.0);
            indx = getindex(p1, queue);
            if (burstT <= q1)
            {
                processes.remove(p1);
                queue.remove(p1);
                LastT=Time;
                Time += burstT;
                indx2 = getindex(p1,TimeHistory);
                TimeHistory.get(indx2).setTurnARoundTime(Time-TimeHistory.get(indx2).getArrivalTime());
                Addarrival(processes, Time,LastT);
                TimeHistory.get(indx2).setProcessTime(burstT);
                p1.AddQuantumHistory(0.0);
                if(!queue.isEmpty())
                    p1 = queue.get(0);
                continue;
            }
            burstT -= q1;
            queue.get(indx).setBurstTime(burstT);
            LastT=Time;
            Time += q1;
            Addarrival(processes, Time,LastT);
            indx2 = getindex(p1,TimeHistory);
            TimeHistory.get(indx2).setProcessTime(q1);
            Process p2 = FindHighPri(queue);
            if (!Compare(p1, p2))
            {
                queue.get(indx).setQuantumTime((quanto + ceil((quanto - q1) / 2)));
                p1.AddQuantumHistory(queue.get(indx).getQuantumTime());
                queue.remove(p1);
                queue.add(p1);
                p1 = p2;

               continue;
            }
            q2 = ceil( (quanto - q1) / 4);
            if (burstT <= q2)
            {
                processes.remove(p1);
                queue.remove(p1);
                LastT=Time;
                Time += burstT;
                indx2 = getindex(p1,TimeHistory);
                TimeHistory.get(indx2).setTurnARoundTime(Time-TimeHistory.get(indx2).getArrivalTime());
                Addarrival(processes, Time,LastT);
                p1.AddQuantumHistory(0.0);
                if(!queue.isEmpty())
                    p1 = queue.get(0);
                continue;
            }
            burstT -= q2;
            queue.get(indx).setBurstTime(burstT);
            LastT=Time;
            Time += q2;
            Addarrival(processes, Time,LastT);
            indx2 = getindex(p1,TimeHistory);
            TimeHistory.get(indx2).setProcessTime(q2);
            p2 = FindLeastBrust(queue);
            q3 = quanto - q1 - q2;
            if (!Compare(p1, p2))
            {
                queue.get(indx).setQuantumTime((quanto + q3));
                p1.AddQuantumHistory(queue.get(indx).getQuantumTime());
                queue.remove(p1);
                queue.add(p1);
                p1 = p2;

                continue;
            }
            if (burstT <= q3)
            {
                processes.remove(p1);
                queue.remove(p1);
                LastT=Time;
                Time += burstT;
                indx2 = getindex(p1,TimeHistory);
                TimeHistory.get(indx2).setTurnARoundTime(Time-TimeHistory.get(indx2).getArrivalTime());
                Addarrival(processes, Time,LastT);
                p1.AddQuantumHistory(0.0);
                if(!queue.isEmpty())
                    p1 = queue.get(0);
                continue;
            }
            burstT -= q3;
            queue.get(indx).setBurstTime(burstT);
            LastT=Time;
            Time += q3;
            Addarrival(processes, Time,LastT);
            indx2 = getindex(p1,TimeHistory);
            TimeHistory.get(indx2).setProcessTime(q3);
            while ( queue.size() == 0 && processes.size()!=0)
            {
                LastT=Time;
                Time++;
                Addarrival(processes, Time,LastT);
                p1 = queue.get(0);

            }



        }

    }





}