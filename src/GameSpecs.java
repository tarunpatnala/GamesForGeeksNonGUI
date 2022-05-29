import java.util.Map;

public class GameSpecs {

    private final double min_price;
    private final double max_price;
    private final Map<Specs, Object> gspecs;
    public GameSpecs(double max_price, double min_price, Map<Specs, Object> gspecs) {
        this.max_price = max_price;
        this.min_price = min_price;
        this.gspecs = gspecs;
    }
    public double getMin_price() {
        return this.min_price;
    }
    public double getMax_price() {
        return this.max_price;
    }
    public Map<Specs,Object> getGSpecs(){ return this.gspecs; }
    public Object getGenre(){ return this.gspecs.get(Specs.Genre); }
    public Object getPlatform(){ return this.gspecs.get(Specs.Platform); }
    public Object getSubgenre(){ return this.gspecs.get(Specs.Subgenre);}
    public Object getRating(){ return this.gspecs.get(Specs.Rating); }

    /*public String getPerspective(){
        if(this.gspecs.get(Specs.Perspective)==null){return null;}
         else{return this.gspecs.get(Specs.Perspective).toString();}
    }
    public String getMultiplayer(){
        if(this.gspecs.get(Specs.Multiplayer)==null) { return null; }
            else {return this.gspecs.get(Specs.Multiplayer).toString();}
    }*/

}



