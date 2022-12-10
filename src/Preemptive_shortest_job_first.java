import java.util.*;
import java.util.Comparator;

class CompareOrderByLength implements Comparator<Process> {
    public int compare(Process p1, Process p2) {
        if (p1.getBurstTime() < p2.getBurstTime()) {
            return -1;
        } else if (p1.getBurstTime() > p2.getBurstTime())
            return 1;
        return 0;
    }
}

public class Preemptive_shortest_job_first extends Scheduler implements Algorithm {
    int current_time = 0;
    private Process last = null;
    private int k = 0;

    PriorityQueue<Process> pq = new PriorityQueue<>(new CompareOrderByLength());

    public void run(Queue<Process> q) {
        while (!q.isEmpty() || !pq.isEmpty()) {
            while (!q.isEmpty() && current_time >= q.peek().getArrivalTime()) {
                pq.add(q.poll());
            }

            if (!pq.isEmpty()) {
                Process p = pq.poll();

                if (p.getBurstTime() > 0) {
                    p.setBurstTime(p.getBurstTime() - 1);
                }
                if (p.getBurstTime() > 0) {
                    pq.add(p);
                }
                if (p != last) {
                    if (last == null)
                        last = p;
                    else {
                        System.out.println("P" + last.getProcessNumber() + ": " + k);
                        last = p;
                        k = 0;
                    }
                }
                k++;
            }
            current_time++;
        }
        System.out.println("P" + last.getProcessNumber() + ": " + k);
    }
}
