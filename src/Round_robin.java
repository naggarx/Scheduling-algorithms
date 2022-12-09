import java.util.Queue;

public class Round_robin implements Algorithm {

    private final int quantum = 4;

    @Override
    public void run(Queue<Process> q) {
        while (!q.isEmpty()) {
            Process p = q.poll();
            int temp_quantum = quantum;
            while (temp_quantum-- > 0 && p.getBurstTime() > 0) {
                p.setBurstTime(p.getBurstTime() - 1);
            }
            if (p.getBurstTime() > 0) {
                q.add(p);
            }
            System.out.println("P" + p.getProcessNumber() + ": " + (quantum - temp_quantum - 1));
        }
    }
}
