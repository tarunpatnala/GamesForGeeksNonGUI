import javax.swing.*;
import java.io.*;
import java.util.*;

public class FindAGame {
    private static Game game = null;
    private static double maximumPrice = 0;
    private static double minimumPrice = 0;
    private static List<Rating> ratingValues = Arrays.asList(Rating.values());
    private static Genre genre = null;
    private static Platform platform = null;
    private static Rating rating = null;
    private static GameSpecs gs = null;
    private static Map<Specs, Object> userSpecs = new HashMap<Specs, Object>();

// main method
    public static void main(String[] args) throws IOException {
        AllGames ag = LoadGameData();
        try {
            genre = Genre.valueOf(JOptionPane.showInputDialog(null, "Choose the Genre of the game:", "The Greek Geek's Game Finder", JOptionPane.QUESTION_MESSAGE, null, Genre.values(), Arrays.stream(Genre.values()).toArray()[0]).toString().toLowerCase().replace(" ", "_"));
            if(!genre.toString().equalsIgnoreCase("na")){
                userSpecs.put(Specs.Genre, genre);
                Object[] subgenre = ag.getAllGenres(genre).toArray();
                if(subgenre.length > 1) {
                    userSpecs.put(Specs.Subgenre, JOptionPane.showInputDialog(null, "Choose the Subgenre", "Subgenre:", JOptionPane.QUESTION_MESSAGE, null, subgenre, subgenre[0]));
                }
            }
                userSpecs.put(Specs.Platform, Platform.valueOf(JOptionPane.showInputDialog(null, "Please select the platform:", "The Greek Geek's Game Finder", JOptionPane.QUESTION_MESSAGE, null, Platform.values(), Arrays.stream(Platform.values()).toArray()[0]).toString().replace(" ","_")));
                userSpecs.put(Specs.Rating, JOptionPane.showInputDialog(null, "Choose the Rating", "The Greek Geek's Game Finder", JOptionPane.QUESTION_MESSAGE, null, ratingValues.toArray(), ratingValues.get(0)).toString());
                minimumPrice = Double.parseDouble(JOptionPane.showInputDialog("Please enter minimum price: "));
                maximumPrice = Double.parseDouble(JOptionPane.showInputDialog("Please enter maximum price: "));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"You have entered an invalid input!!");
        }
        gs = new GameSpecs(maximumPrice, minimumPrice, userSpecs);
        game = new Game(null, 0, 0, gs);
        ArrayList<Game> matchedGames;
        matchedGames = ag.findGames(game);
        if(matchedGames.isEmpty()){
            JOptionPane.showMessageDialog(null,"No games found!!");
        }else {
            String S = "Matches found!! The following games meet your criteria:\n\n";
            for (Game g : matchedGames) {
                S = S+ g.getDescription(game.getGameSpecs().getGSpecs().keySet().stream().toArray(Specs[]::new));
            }
            S = S + "\n Please select which (if any) game you'd like to order:";
            Map<Object, Game> map = new HashMap<Object, Game>();
            for (Game g : matchedGames) {
                map.put(g.getTitle(), g);
            }
            JFrame frame = new JFrame();
            Object Selection = JOptionPane.showInputDialog(frame, S, "The Greek Geek's game finder", JOptionPane.QUESTION_MESSAGE, null, map.keySet().toArray(), map.keySet().toArray()[0]);
            if (Selection != null) {
                JTextField Name = new JTextField(50);
                JTextField PhoneNumber = new JTextField(15);
                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Name:"));
                myPanel.add(Name);
                myPanel.add(Box.createHorizontalStrut(15));
                myPanel.add(new JLabel("Phone Number:"));
                myPanel.add(PhoneNumber);

                int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Name and Phone Number", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Geek g = new Geek(Name.getText(), PhoneNumber.getText());
                    submitOrder(g, map.get(Selection));
                }
            }
        }
        System.exit(200);
    }
    public static AllGames LoadGameData() {
        String games = LoadFile("./allGames_v2.txt");
        String[] arrOfGames = games.split("\n");
        arrOfGames = Arrays.copyOfRange(arrOfGames, 1, arrOfGames.length);
        AllGames Arrgames = new AllGames();
        for (String a : arrOfGames) {
            Map<Specs, Object> gamespecs = new HashMap<Specs, Object>();
            String[] gameInfo = a.split(",", 7);
            String title = gameInfo[0];
            long product_code = Long.parseLong(gameInfo[1]);
            double price = Double.parseDouble(gameInfo[2]);
            gamespecs.put(Specs.Genre, genre.valueOf(gameInfo[3].replace(" ", "_")));
            gamespecs.put(Specs.Platform, platform.valueOf(gameInfo[4].replace(" ", "_")));
            gamespecs.put(Specs.Subgenre, gameInfo[5]);
            gamespecs.put(Specs.Rating, Rating.valueOf(gameInfo[6]).toString());
            GameSpecs gamespec = new GameSpecs(0,0,gamespecs);
            Game game = new Game(title, product_code, price, gamespec);
            Arrgames.addGame(game);
        }
        return Arrgames;

    }
    public static void submitOrder(Geek geek, Game game) throws IOException {
        String fileName = geek.getFullName().replace(" ", "_") + "_" + game.getProduct_code() + ".txt";
        File file = new File(System.getProperty("user.dir") + "/Orders/" + fileName);
        FileWriter order = new FileWriter(file);
        order.write("Order details: \n");
        order.write("\tName: " + geek.getFullName() + "\n");
        order.write("\tPhone number: " + geek.getPhoneNumber() + "\n");
        order.write("\tgame: " + game.getTitle() + " (" + game.getProduct_code() + ")\n");
        order.close();

    }
    private static String LoadFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


}
