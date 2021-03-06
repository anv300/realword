import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class WordChooseScreen extends Screen {
    public final List<String> players;
    public final String chosenPlr;
    JLabel title;

    public WordChooseScreen(List<String> players, Screen prevScreen) {
        super(new JFrame("RealWord - Choose word"), prevScreen);
        this.players = players;
        chosenPlr = players.get(new Random().nextInt(players.size()));
    }

    @Override
    public void draw() {
        JFrame f=frame;

        title = new JLabel("Choose the word, " + chosenPlr);
        title.setBounds(100, 0, 400, 50);
        f.add(title);

        Consumer<String> continu = (word) -> {
            if(word.isEmpty()) return;
            Main.setScreen(new DefChooseScreen(players, word, chosenPlr, this));
        };

        JTextField textField = new JTextField();
        textField.setBounds(130, 100, 130, 50);
        textField.addActionListener((e) -> continu.accept(textField.getText()));
        f.add(textField);

        JButton con = new JButton("Continue");
        con.setBounds(150, 150, 70, 20);
        con.addActionListener((e) -> continu.accept(textField.getText()));
        f.add(con);

        addBackButton();
        addHelp();
        finishDraw();
    }
}
