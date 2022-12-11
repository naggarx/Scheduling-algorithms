import java.util.Vector;

abstract public class Scheduler {


    Vector<Process> TimeHistory = new Vector<>();

    Vector<Process> processes = new Vector<>();
    Vector<String> Order = new Vector<>();


    abstract void input(int numbers,int timeQuantum,int contextSwitching);

    abstract void output();
    abstract void doScheuling();

    public Vector<Process> GetTimeHistroy() {
        return TimeHistory;
    }

    public Vector<String> getOrder() {
        return Order;
    }

    public void AddProcesse(Process p1) {
        this.processes.add(p1);
        this.TimeHistory.add(p1);
    }


}
