package top.wello.health.Request;

public class SuggestRequest extends BaseRequest {

    private int heat;
    private boolean noBreakfast;
    private boolean noLunch;
    private boolean noDiner;
    private boolean noDrink;
    private boolean noFish;

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public boolean isNoBreakfast() {
        return noBreakfast;
    }

    public void setNoBreakfast(boolean noBreakfast) {
        this.noBreakfast = noBreakfast;
    }

    public boolean isNoLunch() {
        return noLunch;
    }

    public void setNoLunch(boolean noLunch) {
        this.noLunch = noLunch;
    }

    public boolean isNoDiner() {
        return noDiner;
    }

    public void setNoDiner(boolean noDiner) {
        this.noDiner = noDiner;
    }

    public boolean isNoDrink() {
        return noDrink;
    }

    public void setNoDrink(boolean noDrink) {
        this.noDrink = noDrink;
    }

    public boolean isNoFish() {
        return noFish;
    }

    public void setNoFish(boolean noFish) {
        this.noFish = noFish;
    }
}
