package meta;

import java.time.temporal.ChronoUnit;

public class GraphMeta {

  private final String graphName;
  private final String yAxisUnit;
  private final ChronoUnit xAxisTimeUnit;

  public GraphMeta(String graphName, String yAxisUnit, ChronoUnit timeUnit) {
    this.graphName = graphName;
    this.yAxisUnit = yAxisUnit;
    this.xAxisTimeUnit = timeUnit;
  }

  public String getGraphName() {
    return graphName;
  }

  public String getyAxisUnit() {
    return yAxisUnit;
  }

  public ChronoUnit getxAxisTimeUnit() {
    return xAxisTimeUnit;
  }
}
