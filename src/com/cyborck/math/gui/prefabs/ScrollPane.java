package com.cyborck.math.gui.prefabs;

import com.cyborck.math.ColorScheme;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

//to understand this visit https://stackoverflow.com/a/33286979

public class ScrollPane extends JScrollPane {
    private static final int SCROLL_BAR_ALPHA_ROLLOVER = 200;
    private static final int SCROLL_BAR_ALPHA = 100;
    private static final int THUMB_SIZE = 8;
    private static final int SB_SIZE = 10;
    private static final Color THUMB_COLOR = ColorScheme.FOREGROUND_1;

    public ScrollPane ( Component view ) {
        super( view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED );

        setBorder( null );
        setLayout( new CustomScrollPaneLayout() );

        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setOpaque( false );
        verticalScrollBar.setUnitIncrement( 2 );
        verticalScrollBar.setUI( new CustomScrollBarUI( this ) );

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setOpaque( false );
        horizontalScrollBar.setUnitIncrement( 2 );
        horizontalScrollBar.setUI( new CustomScrollBarUI( this ) );

        setComponentZOrder( getVerticalScrollBar(), 0 );
        setComponentZOrder( getHorizontalScrollBar(), 1 );
        setComponentZOrder( getViewport(), 2 );
    }

    private static class CustomScrollPaneLayout extends ScrollPaneLayout {
        @Override
        public void layoutContainer ( Container parent ) {
            Rectangle availR = parent.getBounds();

            // viewport
            Insets insets = parent.getInsets();
            availR.x = insets.left;
            availR.y = insets.top;
            availR.width -= insets.left + insets.right;
            availR.height -= insets.top + insets.bottom;
            if ( viewport != null ) {
                viewport.setBounds( availR );
            }

            // vertical scroll bar
            Rectangle vsbR = new Rectangle();
            vsbR.width = SB_SIZE;
            vsbR.height = availR.height - vsbR.width;
            vsbR.x = availR.x + availR.width - vsbR.width;
            vsbR.y = availR.y;
            if ( vsb != null ) {
                vsb.setBounds( vsbR );
            }

            // horizontal scroll bar
            Rectangle hsbR = new Rectangle();
            hsbR.height = SB_SIZE;
            hsbR.width = availR.width - hsbR.height;
            hsbR.x = availR.x;
            hsbR.y = availR.y + availR.height - hsbR.height;
            if ( hsb != null ) {
                hsb.setBounds( hsbR );
            }
        }
    }

    private static class CustomScrollBarUI extends BasicScrollBarUI {
        private final JScrollPane sp;

        public CustomScrollBarUI ( JScrollPane sp ) {
            this.sp = sp;
        }

        @Override
        protected JButton createDecreaseButton ( int orientation ) {
            return createInvisibleButton();
        }

        @Override
        protected JButton createIncreaseButton ( int orientation ) {
            return createInvisibleButton();
        }

        @Override
        protected void paintTrack ( Graphics g, JComponent c, Rectangle trackBounds ) {
        }

        @Override
        protected void paintThumb ( Graphics g, JComponent c, Rectangle thumbBounds ) {
            int alpha = isThumbRollover() ? SCROLL_BAR_ALPHA_ROLLOVER : SCROLL_BAR_ALPHA;
            int orientation = scrollbar.getOrientation();
            int x = thumbBounds.x;
            int y = thumbBounds.y;

            int width = orientation == JScrollBar.VERTICAL ? THUMB_SIZE : thumbBounds.width;
            width = Math.max( width, THUMB_SIZE );

            int height = orientation == JScrollBar.VERTICAL ? thumbBounds.height : THUMB_SIZE;
            height = Math.max( height, THUMB_SIZE );

            Graphics2D graphics2D = ( Graphics2D ) g.create();
            graphics2D.setColor( new Color( THUMB_COLOR.getRed(), THUMB_COLOR.getGreen(), THUMB_COLOR.getBlue(), alpha ) );
            graphics2D.fillRect( x, y, width, height );
            graphics2D.dispose();
        }

        @Override
        protected void setThumbBounds ( int x, int y, int width, int height ) {
            super.setThumbBounds( x, y, width, height );
            sp.repaint();
        }

        private JButton createInvisibleButton () {
            JButton button = new JButton();
            button.setOpaque( false );
            button.setFocusable( false );
            button.setFocusPainted( false );
            button.setBorderPainted( false );
            button.setBorder( BorderFactory.createEmptyBorder() );
            return button;
        }
    }
}
