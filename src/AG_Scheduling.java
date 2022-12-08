import java.util.*;


public class AG_Scheduling
{

   // Vector<Process> Priority= new Vector<>();
   // Vector<Process> BurstTime= new Vector<>();
    Vector<Process> queue= new Vector<>();
    Vector<Process> processes = new Vector<>();
    public Process FindHighPri(Vector<Process> processes)
    {
        Process High = new Process();
        High.setPriority(100000000);
        for (Process process : processes)
        {
            if (process.getPriority() < High.getPriority())
                High = process;
        }
        return High;
    }
    public Process FindLeastBrust(Vector<Process> processes)
    {
        Process Lowest = new Process();
        Lowest.setBurstTime(100000000);
        for (Process process : processes)
        {
            if (process.getBurstTime() < Lowest.getBurstTime())
                Lowest = process;
        }
        return Lowest;
    }








}
