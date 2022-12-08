public class Process
{
    private String Name;
    private int ArrivalTime;
    private int BurstTime;
    private int Priority;
    private int QuantumTime;



    Process()
    {
    }

    Process(String name,int arrive,int burst,int prio,int Quanto)
    {
        this.Name=name;
        this.ArrivalTime=arrive;
        this.BurstTime=burst;
        this.Priority=prio;
        this.QuantumTime=Quanto;
    }
    public void setName(String name)
    {
        this.Name=name;

    }
    public void setArrivalTime(int arrive)
    {
        this.ArrivalTime=arrive;

    }
    public void setBurstTime(int burst)
    {
        this.BurstTime=burst;

    }
    public void setPriority(int prio)
    {
        this.Priority=prio;

    }
    public void setQuantumTime(int Quanto)
    {
        this.QuantumTime=Quanto;

    }
    public String getName()
    {
        return Name;

    }
    public int getArrivalTime()
    {
        return ArrivalTime;

    }
    public int getBurstTime()
    {
        return BurstTime;

    }
    public int getPriority()
    {
       return Priority;

    }
    public int getQuantumTime()
    {
        return QuantumTime;

    }







}
