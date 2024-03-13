package railway_similator.print;

import railway_similator.RailwayNetwork;

public class StatusPrinter implements Print {
    RailwayNetwork railwayNetwork;
    public StatusPrinter(RailwayNetwork railwayNetwork){
        this.railwayNetwork = railwayNetwork;
    }
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void printStatus() {
        while (true) {
            System.out.println(railwayNetwork);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
