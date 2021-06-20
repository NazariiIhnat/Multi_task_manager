package components.custom;

import components.Colors;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class CustomFrame extends JFrame implements Closeable {

    private String frameName;
    private int frameWidth;
    private int labelHeight;
    private JPanel contentPane;

    public CustomFrame(String frameName, int x, int y, int width, int height){
        setBounds(x, y, width, height);
        setTitle(frameName);
        setUndecorated(true);
        setResizable(false);
        this.contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(Colors.HIGHLIGHT_COLOR));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.frameName = frameName;
        JPanel upperPanel = new JPanel();
        frameWidth = getWidth();
        labelHeight = 25;
        upperPanel.setLayout(null);
        upperPanel.setBounds(0, 0, frameWidth, labelHeight);
        upperPanel.setBackground(Colors.SOFT_BLUE);
        upperPanel.add(getHeaderLabel());
        upperPanel.add(minimizeLabel());
        upperPanel.add(getCloseLabel());
        upperPanel.addMouseListener(new FrameDragger(this));
        add(upperPanel);
    }

    private JLabel getHeaderLabel() {
        JLabel label = new JLabel(frameName);
        label.setBounds(10, 0, 100, labelHeight);
        return label;
    }

    private JLabel minimizeLabel() {
        JLabel label = new JLabel("-");
        label.setBounds(frameWidth - 60, 0, 30, labelHeight);
        label.setOpaque(true);
        label.setBackground(Colors.SOFT_BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.addMouseListener(new LabelsPainter());
        label.addMouseListener(new FrameMinimizer(this));
        return label;
    }

    private JLabel getCloseLabel() {
        JLabel label = new JLabel("X");
        label.setBounds(frameWidth - 30, 0, 30, labelHeight);
        label.setOpaque(true);
        label.setBackground(Colors.SOFT_BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.addMouseListener(new LabelsPainter());
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeAction();
            }
        });
        return label;
    }

    public abstract void closeAction();

    public Component add(Component component) {
        contentPane.add(component);
        return component;
    }

    @Override
    public JPanel getContentPane() {
        return this.contentPane;
    }

    class LabelsPainter extends MouseAdapter {

        private Color mouseOverComponentColor = new Color(90, 134, 177);
        private Color mousePressedComponentColor = new Color(135, 177, 199);

        @Override
        public void mousePressed(MouseEvent e) {
            e.getComponent().setBackground(mousePressedComponentColor);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            e.getComponent().setBackground(Colors.SOFT_BLUE);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            e.getComponent().setBackground(mouseOverComponentColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(Colors.SOFT_BLUE);
        }
    }

    class FrameDragger extends MouseAdapter{
        private JFrame frameToDrag;
        private int xMouse;
        private int yMouse;

        public FrameDragger(JFrame frameToDrag) {
            this.frameToDrag = frameToDrag;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            xMouse = e.getX();
            yMouse = e.getY();
            e.getComponent().addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    int x = e.getXOnScreen();
                    int y = e.getYOnScreen();
                    frameToDrag.setLocation(x - xMouse, y - yMouse);
                }
            });
        }
    }

    class FrameMinimizer extends MouseAdapter{

        private JFrame frame;

        public FrameMinimizer(JFrame frameToMinimize){
            frame = frameToMinimize;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            frame.setState(JFrame.ICONIFIED);
        }
    }

}
