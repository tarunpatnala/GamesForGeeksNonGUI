import java.util.HashMap;
import java.util.Map;

public class Game {

    private final String title;
    private final long product_code;
    private final double price;
    private final GameSpecs gs;

    public Game(String title, long product_code, double price, GameSpecs gs) {
        this.title = title;
        this.product_code = product_code;
        this.price = price;
        this.gs = gs;
    }

    public String getTitle() {
        return this.title;
    }

    public long getProduct_code() {
        return this.product_code;
    }

    public double getPrice() {
        return this.price;
    }

    public GameSpecs getGameSpecs() {
        return this.gs;
    }

    public String getDescription(Specs[] spec) {
        String S ="";
        S= S + getTitle() + " (" + getProduct_code() + ")\n";
        for(Specs s: spec){
            S = S+" > "+s+": " + this.gs.getGSpecs().get(s) + "\n";
        }
        S = S+ " > Price: $" + String.format("%.2f",getPrice()) + "\n\n";
        return S;
    }

}
