import java.util.Vector;

public class Process
{
    private String Name;
    private double ArrivalTime;
    private double BurstTime;
    private int Priority;
    private double QuantumTime;
    private double WaitingTime;
    private double TurnARoundTime;
    private double ProcessTime=0;

    Vector<Double> QuantumHistory = new Vector<>();




    Process()
    {
    }

    Process(String name,double arrive,double burst,int prio,double Quanto)
    {
        this.Name=name;
        this.ArrivalTime=arrive;
        this.BurstTime=burst;
        this.Priority=prio;
        this.QuantumTime=Quanto;
    }
    public Vector<Double> getQuantumHistory() {
        return QuantumHistory;
    }

    public void AddQuantumHistory(Double H) {
        this.QuantumHistory.add(H);

    }
    public void setName(String name)
    {
        this.Name=name;

    }
    public void setArrivalTime(int arrive)
    {
        this.ArrivalTime=arrive;

    }
    public void setBurstTime(double burst)
    {
        this.BurstTime=burst;

    }
    public void setPriority(int prio)
    {
        this.Priority=prio;

    }
    public void setQuantumTime(double Quanto)
    {
        this.QuantumTime=Quanto;

    }
    public String getName()
    {
        return Name;

    }
    public double getArrivalTime()
    {
        return ArrivalTime;

    }
    public double getBurstTime()
    {
        return BurstTime;

    }
    public int getPriority()
    {
       return Priority;

    }
    public double getQuantumTime()
    {
        return QuantumTime;

    }


    public double getProcessTime() {
        return ProcessTime;
    }

    public void setProcessTime(double processTime) {
        ProcessTime += processTime;
    }

    public double getTurnARoundTime() {
        return TurnARoundTime;
    }

    public void setTurnARoundTime(double turnARoundTime) {
        TurnARoundTime = turnARoundTime;
    }

    public double getWaitingTime() {
        return WaitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        WaitingTime = waitingTime;
    }
}
