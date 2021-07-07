package components.custom;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class CustomFrame extends JFrame {

    private static final int UPPER_PANEL_HEIGHT = 25;
    private JPanel contentPane;
    private JLabel minimizeLabel;
    private JLabel closeLabel;
    private JLabel headerLabel;
    private JPanel upperPanel;

    public CustomFrame(String frameName, int x, int y, int width, int height){
        setBounds(x, y, width, height);
        setName(frameName);
        setTitle(frameName);
        setUndecorated(true);
        setResizable(false);
        initContentPane();
        initHeaderLabel();
        initMinimizeLabel();
        initCloseLabel();
        initUpperPanel();
    }

    private void initContentPane() {
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(Colors.HIGHLIGHT_COLOR));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    private void initHeaderLabel() {
        headerLabel = new JLabel(getName());
        headerLabel.setBounds(10, 0, 100, UPPER_PANEL_HEIGHT);
    }

    private void initMinimizeLabel() {
        minimizeLabel = new JLabel("-");
        minimizeLabel.setBounds(getWidth() - 60, 0, 30, UPPER_PANEL_HEIGHT);
        minimizeLabel.setOpaque(true);
        minimizeLabel.setBackground(Colors.SOFT_BLUE);
        minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minimizeLabel.addMouseListener(new LabelsPainter());
        minimizeLabel.addMouseListener(new FrameMinimizer(this));
    }

    private void initCloseLabel() {
        closeLabel = new JLabel("X");
        closeLabel.setBounds(getWidth() - 30, 0, 30, UPPER_PANEL_HEIGHT);
        closeLabel.setOpaque(true);
        closeLabel.setBackground(Colors.SOFT_BLUE);
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.addMouseListener(new LabelsPainter());
    }

    private void initUpperPanel() {
        upperPanel = new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0, 0, getWidth(), UPPER_PANEL_HEIGHT);
        upperPanel.setBackground(Colors.SOFT_BLUE);
        upperPanel.addMouseListener(new FrameDragger(this));
        upperPanel.add(headerLabel);
        upperPanel.add(minimizeLabel);
        upperPanel.add(closeLabel);
        add(upperPanel);
    }

    public Component add(Component component) {
        contentPane.add(component);
        return component;
    }

    public void setCloseAction(MouseAdapter action) {
        closeLabel.addMouseListener(action);
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
