import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Jack on 2015/8/15.
 */
public class PolyPlot extends JFrame{
    private String title;
    private int WIDTH = 3*(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4;
    private int HEIGHT = 3*(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4;
    public PolyPlot(Polyfit polyfit) {
        int order = polyfit.getOrder();
    }

    public PolyPlot(double[] x, double[] y) {

        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);
        PlotPanel plotPanel = new PlotPanel(x,y,WIDTH,HEIGHT);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(plotPanel);
        this.setVisible(true);
    }
}

class PlotPanel extends JPanel {
    double[] x;
    double[] y;
    int width;
    int height;

    public PlotPanel(double[] x, double[] y, int width, int height) {
        if(x.length != y.length) {
            System.out.println("Input Error!");
        } else {
            this.x = new double[x.length];
            this.y = new double[y.length];
            this.width = width;
            this.height = height;
            this.x = translateX(x);
            this.y = translateY(y);
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        //绘制坐标系
        graphics.drawLine(0,height/2, width,height/2);
        graphics.drawLine(width/2,0,width/2, height);

        //绘制三角形坐标头
        Polygon xPolygon = new Polygon();
        xPolygon.addPoint(width/2,0);
        xPolygon.addPoint(width / 2 - 10, 20);
        xPolygon.addPoint(width / 2 + 10, 20);
        graphics.setColor(Color.black);
        graphics.fillPolygon(xPolygon);

        Polygon yPolygon = new Polygon();
        yPolygon.addPoint(width,height/2);
        yPolygon.addPoint(width-20, height/2+10);
        yPolygon.addPoint(width-20, height/2-10);
        graphics.fillPolygon(yPolygon);

        //绘制坐标网格
/*        double step = width - 20
        for(int i = 0; i < width; i = i + 10) {
            graphics.drawLine(i,0,i,height);
        }

        for(int i = 0; i < height; i = i + 10) {
            graphics.drawLine(0,i,width,i);
        }*/
        //绘制曲线
        System.out.println(x.length);
        for(int i = 0; i < x.length - 1; i++) {
            Line2D.Double line = new Line2D.Double(x[i], y[i],x[i+1],y[i+1]);
            graphics2D.draw(line);
        }
    }

    private double translateX(double x) {
        return x + width/2;
    }

    private  double[] translateX(double[] x) {
        for (int i = 0; i < x.length; i++) {
            x[i] = translateX(x[i]);
        }
        return  x;
    }
    private double translateY(double y) {
        return height/2 - y;
}
    private double[] translateY(double[] y) {
        for(int i = 0; i < y.length; i++) {
            y[i] = translateY(y[i]);
        }
        return y;
    }
}
