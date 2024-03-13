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
        long printAgainOnOrAfter = System.currentTimeMillis();
        while (true){
            if (System.currentTimeMillis() >= printAgainOnOrAfter){
                System.out.println(railwayNetwork);
                printAgainOnOrAfter += (System.currentTimeMillis() + 15000);
            }
        }
    }
}
