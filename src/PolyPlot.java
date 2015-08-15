import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Jack on 2015/8/15.
 */
public class PolyPlot extends JFrame{
    private String title;

    public PolyPlot(Polyfit polyfit) {
        int order = polyfit.getOrder();
    }

    public PolyPlot(double[] x, double[] y) {
        PlotPanel plotPanel = new PlotPanel(x,y);

    }
}

class PlotPanel extends JPanel {
    double[] x;
    double[] y;
    public PlotPanel(double[] x, double[] y) {
        if(x.length != y.length) {
            System.out.println("Input Error!");
        } else {
            this.x = new double[x.length];
            this.y = new double[y.length];
            for(int i = 0; i < x.length; i++) {
                this.x[i] = x[i];
                this.y[i] = y[i];
            }
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;
        for(int i = 0; i < x.length - 1; i++) {
            Line2D.Double line = new Line2D.Double(x[i], y[i],x[i+1],y[i+1]);
            graphics2D.draw(line);
        }
    }
}
