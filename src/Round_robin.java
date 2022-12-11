import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Round_robin extends Scheduler implements Algorithm {

    Process last = null;
    Queue<Process> q = new ArrayDeque<>();
    private int contextSwitching, totalContextSwitching = 0;

    void setContextSwitching(int contextSwitching) {
        this.contextSwitching = contextSwitching;
    }

    int getTotalContextSwitching() {
        return totalContextSwitching;
    }

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

    @Override
    public void run() {
        while (!q.isEmpty()) {
            Process p = q.poll();
            if (last != p) {
                totalContextSwitching += contextSwitching;
                last = p;
            }
            double temp_quantum = p.getQuantumTime();
            double ternTime = 0;
            while (temp_quantum-- > 0 && p.getBurstTime() > 0) {
                p.setBurstTime(p.getBurstTime() - 1);
                ternTime++;
            }

            if (p.getBurstTime() > 0) {
                q.add(p);
            } else
                p.setTurnARoundTime(p.getTurnARoundTime() + ternTime);

            for (Process process : q) {
                process.setTurnARoundTime(process.getTurnARoundTime() + ternTime);
            }

            Order.add(p.getName());
//            System.out.println("P" + p.getProcessNumber() + ": " + (p.getQuantumTime() - temp_quantum - 1));
        }
    }
}
