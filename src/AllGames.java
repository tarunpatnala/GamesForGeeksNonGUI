import java.util.*;

public class AllGames {


    ArrayList<Game> games = new ArrayList<Game>();

    public void addGame(Game game) {
        games.add(game);
    }


    public ArrayList<Game> findGames(Game game) {
        ArrayList<Game> matchedGames = new ArrayList<Game>();
        for (Game sourceGame : games) {
            Map<Specs, Object> sGame = new HashMap<>();
            for(Specs s: sourceGame.getGameSpecs().getGSpecs().keySet()){
                if(game.getGameSpecs().getGSpecs().containsKey(s)){
                    sGame.put(s,sourceGame.getGameSpecs().getGSpecs().get(s));
                }
            }
            HashSet<Object> set1 = new HashSet<>(game.getGameSpecs().getGSpecs().values());
            HashSet<Object> set2 = new HashSet<>(sGame.values());
            if (set1.equals(set2)) {
                if(sourceGame.getPrice()<=game.getGameSpecs().getMax_price() && sourceGame.getPrice()>=game.getGameSpecs().getMin_price())
                { matchedGames.add(sourceGame); }
            }
        }
        return matchedGames;
    }

    public Set<Object> getAllGenres(Genre genre){
        Set<Object> subgenre = new HashSet<Object>();
        for(Game game: games){
            if(Objects.equals(game.getGameSpecs().getGenre(), genre)){
                subgenre.add(game.getGameSpecs().getSubgenre());
            }
        }
        return subgenre;
    }
}