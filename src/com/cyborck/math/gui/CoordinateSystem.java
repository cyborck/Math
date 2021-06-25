package com.cyborck.math.gui;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.mathSystem.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class CoordinateSystem extends JPanel implements MouseWheelListener, MouseMotionListener {
    private static final double MIN_SCALE = 0.002;
    private static final double MAX_SCALE = 2;

    private final int width;
    private final int height;

    private final List<Function> functions;
    private final List<Color> colors;

    private double offsetX;
    private double offsetY;
    private double scale;

    private int mouseX;
    private int mouseY;

    public CoordinateSystem ( int width, int height ) {
        functions = new ArrayList<>();
        colors = new ArrayList<>();

        offsetX = width / 2d;
        offsetY = height / 2d;
        scale = 0.02;

        this.width = width;
        this.height = height;

        setPreferredSize( new Dimension( width, height ) );
        addMouseWheelListener( this );
        addMouseMotionListener( this );
    }

    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent( g );

        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( ColorScheme.SMALL_TEXT_FONT );
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

        g2d.setColor( ColorScheme.BACKGROUND_2 );
        g2d.fillRect( 0, 0, width, height );

        //draw coordinate system
        Line2D xAxis = new Line2D.Double( 0, offsetY, width, offsetY );
        Line2D yAxis = new Line2D.Double( offsetX, 0, offsetX, height );
        g2d.setColor( ColorScheme.FOREGROUND_1 );
        g2d.draw( xAxis );
        g2d.draw( yAxis );

        //label axis and draw grid
        double gridSpacing = 1;
        while ( gridSpacing < 50d * scale ) gridSpacing *= 2;
        while ( gridSpacing > 100d * scale ) gridSpacing /= 2;
        //positive x
        for ( double x = gridSpacing; x < ( width - offsetX ) * scale; x += gridSpacing ) {
            double xCoordinate = x / scale + offsetX;

            //grid line
            g2d.setColor( ColorScheme.BACKGROUND_1 );
            Line2D gridLine = new Line2D.Double( xCoordinate, 0, xCoordinate, height );
            g2d.draw( gridLine );

            g2d.setColor( ColorScheme.FOREGROUND_1 );

            //little white line
            double y1 = offsetY - 5;
            double y2 = offsetY + 5;
            Line2D whiteLine = new Line2D.Double( xCoordinate, y1, xCoordinate, y2 );
            g2d.draw( whiteLine );

            //label
            String str = String.valueOf( x );
            int strWidth = g2d.getFontMetrics().stringWidth( str );
            int strX = ( int ) ( xCoordinate - strWidth / 2d - 1 );
            int strY = ( int ) ( y1 - 4 );
            g2d.drawString( str, strX, strY );
        }
        //negative x
        for ( double x = -gridSpacing; x > -offsetX * scale; x -= gridSpacing ) {
            double xCoordinate = x / scale + offsetX;

            //grid line
            g2d.setColor( ColorScheme.BACKGROUND_1 );
            Line2D gridLine = new Line2D.Double( xCoordinate, 0, xCoordinate, height );
            g2d.draw( gridLine );

            g2d.setColor( ColorScheme.FOREGROUND_1 );

            //little white line
            double y1 = offsetY - 5;
            double y2 = offsetY + 5;
            Line2D whiteLine = new Line2D.Double( xCoordinate, y1, xCoordinate, y2 );
            g2d.draw( whiteLine );

            //label
            String str = String.valueOf( x );
            int strWidth = g2d.getFontMetrics().stringWidth( str );
            int strX = ( int ) ( xCoordinate - strWidth / 2d - 1 );
            int strY = ( int ) ( y1 - 4 );
            g2d.drawString( str, strX, strY );
        }
        //positive y
        for ( double y = gridSpacing; y < offsetY * scale; y += gridSpacing ) {
            double yCoordinate = -y / scale + offsetY;

            //grid line
            g2d.setColor( ColorScheme.BACKGROUND_1 );
            Line2D gridLine = new Line2D.Double( 0, yCoordinate, width, yCoordinate );
            g2d.draw( gridLine );

            g2d.setColor( ColorScheme.FOREGROUND_1 );

            //little white line
            double x1 = offsetX - 5;
            double x2 = offsetX + 5;
            Line2D whiteLine = new Line2D.Double( x1, yCoordinate, x2, yCoordinate );
            g2d.draw( whiteLine );

            //label
            String str = String.valueOf( y );
            int strHeight = g2d.getFontMetrics().getHeight();
            int strX = ( int ) ( x2 + 2 );
            int strY = ( int ) ( yCoordinate + strHeight / 4 );
            g2d.drawString( str, strX, strY );
        }
        //negative y
        for ( double y = -gridSpacing; y > -( height - offsetY ) * scale; y -= gridSpacing ) {
            double yCoordinate = -y / scale + offsetY;

            //grid line
            g2d.setColor( ColorScheme.BACKGROUND_1 );
            Line2D gridLine = new Line2D.Double( 0, yCoordinate, width, yCoordinate );
            g2d.draw( gridLine );

            g2d.setColor( ColorScheme.FOREGROUND_1 );

            //little white line
            double x1 = offsetX - 5;
            double x2 = offsetX + 5;
            Line2D whiteLine = new Line2D.Double( x1, yCoordinate, x2, yCoordinate );
            g2d.draw( whiteLine );

            //label
            String str = String.valueOf( y );
            int strHeight = g2d.getFontMetrics().getHeight();
            int strX = ( int ) ( x2 + 2 );
            int strY = ( int ) ( yCoordinate + strHeight / 4 );
            g2d.drawString( str, strX, strY );
        }
        g2d.setColor( ColorScheme.FOREGROUND_1 );
        //x
        String xString = "x";
        int xHeight = g2d.getFontMetrics().getHeight();
        int xWidth = g2d.getFontMetrics().stringWidth( xString );
        int xPosX = width - xWidth - 2;
        int xPosY = ( int ) ( offsetY + xHeight + 2 );
        g2d.drawString( xString, xPosX, xPosY );
        //y
        String yString = "y";
        int yHeight = g2d.getFontMetrics().getHeight();
        int yWidth = g2d.getFontMetrics().stringWidth( yString );
        int yPosX = ( int ) ( offsetX - yWidth - 10 );
        int yPosY = yHeight - 4;
        g2d.drawString( yString, yPosX, yPosY );

        //draw every function
        int legendX = width;
        int legendY = height - 10;
        for ( int i = 0; i < functions.size(); i++ ) {
            g2d.setColor( colors.get( i ) );

            //get coordinates
            double[] xCoordinates = new double[ width ];
            double[] yCoordinates = new double[ width ];
            int j = 0;
            for ( double x = -offsetX * scale; x < ( width - offsetX ) * scale && j < width; x += scale ) {
                xCoordinates[ j ] = offsetX + x / scale;
                yCoordinates[ j ] = offsetY - functions.get( i ).get( x ) / scale;
                j++;
            }

            //connect coordinates
            for ( int k = 0; k < xCoordinates.length - 1; k++ )
                if ( !Double.isNaN( yCoordinates[ k ] ) && !Double.isNaN( yCoordinates[ k + 1 ] ) ) {
                    Line2D line = new Line2D.Double( xCoordinates[ k ], yCoordinates[ k ], xCoordinates[ k + 1 ], yCoordinates[ k + 1 ] );
                    g2d.draw( line );
                }

            //draw legend
            String legend = functions.get( i ).getName();
            legendX -= g2d.getFontMetrics().stringWidth( legend ) + 5;
            g2d.drawString( legend, legendX, legendY );
        }
    }

    public void addFunction ( Function function ) {
        functions.add( function );

        //select random color for graph
        int randomR;
        int randomG;
        int randomB;

        do {
            randomR = ( int ) ( Math.random() * 256 );
            randomG = ( int ) ( Math.random() * 256 );
            randomB = ( int ) ( Math.random() * 256 );
        } while ( ( randomR > ColorScheme.BACKGROUND_2.getRed() - 5 && randomR < ColorScheme.BACKGROUND_2.getRed() + 5 ) &&
                ( randomG > ColorScheme.BACKGROUND_2.getGreen() - 5 && randomG < ColorScheme.BACKGROUND_2.getGreen() + 5 ) &&
                ( randomB > ColorScheme.BACKGROUND_2.getBlue() - 5 && randomB < ColorScheme.BACKGROUND_2.getBlue() + 5 ) );

        colors.add( new Color( randomR, randomG, randomB ) );

        repaint();
    }

    public void removeFunction ( Function function ) {
        int index = functions.indexOf( function );
        if ( index >= 0 ) {
            functions.remove( index );
            colors.remove( index );
        }

        repaint();
    }

    private void moveOffsets ( int x, int y ) {
        offsetX += x;
        offsetY += y;
        repaint();
    }

    private void zoomIn () {
        scale /= 1.1;
        scale = Math.max( MIN_SCALE, scale );
        repaint();
    }

    private void zoomOut () {
        scale *= 1.1;
        scale = Math.min( MAX_SCALE, scale );
        repaint();
    }

    @Override
    public void mouseDragged ( MouseEvent e ) {
        int xDiff = e.getX() - mouseX;
        int yDiff = e.getY() - mouseY;

        offsetX += xDiff;
        offsetY += yDiff;
        repaint();

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved ( MouseEvent e ) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved ( MouseWheelEvent e ) {
        if ( e.getWheelRotation() == -1 ) zoomIn();
        else if ( e.getWheelRotation() == 1 ) zoomOut();
    }
}
