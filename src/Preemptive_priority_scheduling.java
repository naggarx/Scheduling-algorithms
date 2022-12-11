import java.util.*;

class CompareOrderByPriority implements Comparator<Process> {
    public int compare(Process p1, Process p2) {
        if (p1.getPriority() < p2.getPriority()) {
            return -1;
        } else if (p1.getPriority() > p2.getPriority())
            return 1;
        return 0;
    }
}

public class Preemptive_priority_scheduling extends Scheduler implements Algorithm {

    PriorityQueue<Process> pq = new PriorityQueue<>(new CompareOrderByPriority());

    int current_time = 0;

    private Process last = null;
    Queue<Process> q = new ArrayDeque<>();
    int starvation = 0;

    private int contextSwitching, totalContextSwitching = 0;


    void setContextSwitching(int contextSwitching) {
        this.contextSwitching = contextSwitching;
    }

    int getTotalContextSwitching() {
        return totalContextSwitching;
    }

    @Override
    public void run() {
        while (!q.isEmpty() || !pq.isEmpty()) {
            if (!q.isEmpty() && current_time >= q.peek().getArrivalTime()) {
                Process p = q.poll();
                p.setPriority(p.getPriority() - starvation++);
                pq.add(p);
            }
            if (!pq.isEmpty()) {
                Process p = pq.poll();
                p.setBurstTime(p.getBurstTime() - 1);
                if (p != last) {
                    totalContextSwitching += contextSwitching;
                    if (last == null)
                        last = p;
                    else {
                        Order.add(last.getName());
                        last = p;
                    }
                }
                if (p.getBurstTime() != 0)
                    pq.add(p);
                else p.setTurnARoundTime(p.getTurnARoundTime() + 1);
                for (Process process : pq) {
                    process.setTurnARoundTime(process.getTurnARoundTime() + 1);
                }
            }
        }
        current_time++;
        Order.add(last.getName());

    }

    @Override
    void input(int number, int timeQuantum, int contextSwitching) {
        Scanner scan = new Scanner(System.in);

        String name;
        double arrival, Burst;
        this.contextSwitching = contextSwitching;
        int prio;
        for (int i = 0; i < number; i++) {
            System.out.println("Enter the Name of Process : ");
            System.out.println();
            name = scan.next();
            System.out.println("Enter the Arrival Time of Process : ");
            arrival = scan.nextInt();
            System.out.println("Enter the Burst Time of Process : ");
            Burst = scan.nextInt();
            System.out.println("Enter the Priority of Process : ");
            prio = scan.nextInt();
            Process p1 = new Process(name, arrival, Burst, prio);
            p1.setQuantumTime(timeQuantum);
            processes.add(p1);
            q.add(p1);

        }
    }

    @Override
    void output() {
        System.out.println("Processes execution order : ");
        for (String s : Order) {
            System.out.print(s + " , ");
        }
        System.out.println();
        double TotalATime = 0;
        double TotalWTime = 0;
        System.out.println("Turnaround Time and Waiting Time for each process : ");
        for (Process P : processes) {

            P.setWaitingTime((P.getTurnARoundTime() - P.getProcessTime()));
            System.out.println(P.getName() + " Turn Around Time: " + P.getTurnARoundTime() + " Waiting Time: " + P.getWaitingTime());
            TotalATime += P.getTurnARoundTime();
            TotalWTime += P.getWaitingTime();
        }
        System.out.println("Average Total Time : " + TotalATime / processes.size());
        System.out.println("Average Waiting Time : " + TotalWTime + getTotalContextSwitching() / processes.size());


    }

    @Override
    void doScheuling() {
        run();
    }
}
