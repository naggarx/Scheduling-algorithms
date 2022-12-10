import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class CompareOrderByPriority implements Comparator<Process> {
    public int compare(Process p1, Process p2) {
        if (p1.getPriority() < p2.getPriority()) {
            return -1;
        } else if (p1.getPriority() > p2.getPriority())
            return 1;
        return 0;
    }
}

public class Preemptive_priority_scheduling extends Scheduler  implements Algorithm {

    PriorityQueue<Process> pq = new PriorityQueue<>(new CompareOrderByPriority());

    private Process last = null;

    int k = 0;

    @Override
    public void run(Queue<Process> q) {
        while (!q.isEmpty() || !pq.isEmpty()) {
            if (!q.isEmpty())
                pq.add(q.poll());

            if (!pq.isEmpty()) {
                Process p = pq.poll();
                p.setBurstTime(p.getBurstTime() - 1);

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
        }
    }
}
