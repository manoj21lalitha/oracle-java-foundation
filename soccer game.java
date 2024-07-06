package gui.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Enum for representing player positions
enum Position {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    STRIKER
}

// Class to represent a soccer player
class Player {
    private String name;
    private Position position;
    private int points; // Points scored by the player

    public Player(String name, Position position) {
        this.name = name;
        this.position = position;
        this.points = 0; // Initialize points to zero
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public void score() {
        points++;
    }
}

// Class to represent a soccer field
class SoccerField {
    private Player[] homeTeam;
    private Player[] awayTeam;
    private int homeScore;
    private int awayScore;

    public SoccerField(Player[] homeTeam, Player[] awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void scoreGoal(boolean isHomeTeam) {
        if (isHomeTeam) {
            homeScore++;
            // Randomly select a player to score a point for the home team
            int randomIndex = (int) (Math.random() * homeTeam.length);
            homeTeam[randomIndex].score();
        } else {
            awayScore++;
            // Randomly select a player to score a point for the away team
            int randomIndex = (int) (Math.random() * awayTeam.length);
            awayTeam[randomIndex].score();
        }
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Player[] getHomeTeam() {
        return homeTeam;
    }

    public Player[] getAwayTeam() {
        return awayTeam;
    }
}

public class SoccerGameSwing extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SoccerField soccerField;
    private JLabel scoreLabel;
    private JPanel fieldPanel;

    public SoccerGameSwing() {
        super("Soccer Game");

        // Sample players
        Player[] homePlayers = {
                new Player("vinusha", Position.GOALKEEPER),
                new Player("gayu", Position.DEFENDER),
                new Player("varshini", Position.MIDFIELDER),
                new Player("banu", Position.STRIKER)
        };

        Player[] awayPlayers = {
                new Player("esha", Position.GOALKEEPER),
                new Player("manojini", Position.DEFENDER),
                new Player("kanagan", Position.MIDFIELDER),
                new Player("karthik", Position.STRIKER)
        };

        // Create a soccer field
        soccerField = new SoccerField(homePlayers, awayPlayers);

        // Create panel for score display
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: 0 - 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scorePanel.add(scoreLabel);

        // Create panel for field display
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2, 4, 10, 10)); // 2 rows, 4 columns

        // Add player visual representations to the field panel
        for (Player player : homePlayers) {
            JLabel playerLabel = createPlayerLabel(player.getName(), player.getPoints());
            fieldPanel.add(playerLabel);
        }

        for (Player player : awayPlayers) {
            JLabel playerLabel = createPlayerLabel(player.getName(), player.getPoints());
            fieldPanel.add(playerLabel);
        }

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        JButton homeGoalButton = new JButton("Home Team Scores!");
        homeGoalButton.addActionListener(this);
        JButton awayGoalButton = new JButton("Away Team Scores!");
        awayGoalButton.addActionListener(this);
        buttonPanel.add(homeGoalButton);
        buttonPanel.add(awayGoalButton);

        // Set up main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scorePanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Home Team Scores!")) {
            soccerField.scoreGoal(true);
        } else if (e.getActionCommand().equals("Away Team Scores!")) {
            soccerField.scoreGoal(false);
        }
        updateScoreLabel();
        updateFieldPanel();
    }

    private void updateScoreLabel() {
        int homeScore = soccerField.getHomeScore();
        int awayScore = soccerField.getAwayScore();
        scoreLabel.setText("Score: " + homeScore + " - " + awayScore);
    }

    private void updateFieldPanel() {
        // Update the points displayed for each player in the field panel
        for (Component component : fieldPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String playerName = label.getText().split(": ")[0]; // Extract player name
                Player[] homePlayers = soccerField.getHomeTeam();
                Player[] awayPlayers = soccerField.getAwayTeam();
                for (Player player : homePlayers) {
                    if (player.getName().equals(playerName)) {
                        label.setText(player.getName() + ": " + player.getPoints() + " points");
                    }
                }
                for (Player player : awayPlayers) {
                    if (player.getName().equals(playerName)) {
                        label.setText(player.getName() + ": " + player.getPoints() + " points");
                    }
                }
            }
        }
    }

    private JLabel createPlayerLabel(String playerName, int points) {
        JLabel label = new JLabel(playerName + ": " + points + " points", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SoccerGameSwing();
        });
    }
}