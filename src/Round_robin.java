import java.util.Queue;

public class Round_robin extends Scheduler implements Algorithm {

    @Override
    public void run(Queue<Process> q) {
        while (!q.isEmpty()) {
            Process p = q.poll();
            double temp_quantum = p.getQuantumTime();
            while (temp_quantum-- > 0 && p.getBurstTime() > 0) {
                p.setBurstTime(p.getBurstTime() - 1);
            }
            if (p.getBurstTime() > 0) {
                q.add(p);
            }
            System.out.println("P" + p.getProcessNumber() + ": " + (p.getQuantumTime() - temp_quantum - 1));
        }
    }
}
