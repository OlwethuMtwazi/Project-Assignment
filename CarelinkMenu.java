/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package za.ac.cput.mainmenuproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * skeleton code
 *
 * @author olwethu mtwazi
 */
public class CarelinkMenu extends JFrame {

    private JLabel welcomeLabel;
    private JLabel nextSessionLabel;
    private JButton profileBtn;
    private JButton appointmentsBtn;
    private JButton feedbackBtn;
    private JButton logoutBtn;

    public CarelinkMenu(int userId) {
        setTitle("CareLink for CPUT");
        setSize(420, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colors
        Color PRIMARY = new Color(41, 69, 114);   // header blue
        Color ACCENT = new Color(139, 195, 74);   // button green
        Color BG = Color.WHITE;                   // background white

        //header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY);
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel title = new JLabel("CareLink for CPUT");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        // (optional logo on right)
        // JLabel logo = new JLabel(new ImageIcon("CareLinkLogo.png"));
        // header.add(logo, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(BG);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        welcomeLabel = new JLabel("Good Morning, User!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Card for next session
        JPanel sessionCard = new JPanel();
        sessionCard.setLayout(new BoxLayout(sessionCard, BoxLayout.Y_AXIS));
        sessionCard.setBackground(new Color(245, 245, 245));
        sessionCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel sessionTitle = new JLabel("Upcoming Session");
        sessionTitle.setFont(new Font("Arial", Font.BOLD, 16));
        sessionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        nextSessionLabel = new JLabel("Loading...");
        nextSessionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nextSessionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sessionCard.add(sessionTitle);
        sessionCard.add(Box.createRigidArea(new Dimension(0, 10)));
        sessionCard.add(nextSessionLabel);

        // Add to center
        centerPanel.add(welcomeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(sessionCard);
        
        // Add Support Panel
        JPanel supportPanel = createSupportPanel();
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(supportPanel);
        
        
        // Add Sessions Panel
        JPanel sessionsPanel = createSessionsPanel();
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(sessionsPanel);

        add(centerPanel, BorderLayout.CENTER);

        // bottom buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        bottomPanel.setBackground(BG);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        profileBtn = new RoundedButton("Profile", ACCENT);
        appointmentsBtn = new RoundedButton("Appointments", ACCENT);
        feedbackBtn = new RoundedButton("Feedback", ACCENT);
        logoutBtn = new RoundedButton("Logout", PRIMARY);

        bottomPanel.add(profileBtn);
        bottomPanel.add(appointmentsBtn);
        bottomPanel.add(feedbackBtn);
        bottomPanel.add(logoutBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load DB data
        loadUserData(userId);
    }

    private JPanel createSupportPanel() {
        JPanel supportPanel = new JPanel();
        supportPanel.setLayout(new BorderLayout());
        Border supportBorder = BorderFactory.createTitledBorder("Support for Students, Staff, and Lecturers");
        supportPanel.setBorder(supportBorder);
        JLabel supportLabel = new JLabel("<html>Your safe space for mental wellbeing—confidential and easy to use.<br>" +
                "Find expert help, schedule appointments with ease and personalized resources to help you cope with stress and thrive.</html>");
        supportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        supportPanel.add(supportLabel, BorderLayout.CENTER);
        return supportPanel;
    }
    
    private JPanel createSessionsPanel() {
        JPanel sessionsPanel = new JPanel();
        sessionsPanel.setLayout(new BorderLayout());
        Border sessionsBorder = BorderFactory.createTitledBorder("How Sessions Work");
        sessionsPanel.setBorder(sessionsBorder);
        JLabel sessionsLabel = new JLabel("<html>Private and confidential online counselling sessions.<br>" +
                "Support tailored to university students, staff, and lecturers.<br>" +
                "Safe space to talk about feelings, stress, anxiety, and more.</html>");
        sessionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sessionsPanel.add(sessionsLabel, BorderLayout.CENTER);
        return sessionsPanel;
    }
    
    //helper methods
    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14)); // Arial for buttons
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    private void loadUserData(int userId) {
        welcomeLabel.setText("Good Morning, Alice!");
        nextSessionLabel.setText("2025-08-01 at 10:00 with Dr. Zulu (Virtual)");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CarelinkMenu(1).setVisible(true);
        });
    }
}
   
//custom rounded button idk if i should make a separate class for this
class RoundedButton extends JButton {
    private int radius = 25;

    public RoundedButton(String text, Color bg) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(bg);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g2);
        g2.dispose();
    }
}