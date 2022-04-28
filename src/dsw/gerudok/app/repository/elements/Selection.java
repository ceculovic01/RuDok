package dsw.gerudok.app.repository.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Selection implements Serializable {

    private List<Slot> activeSlotList;
    private Integer x, y, width, height;

    public Selection() {
        activeSlotList = new ArrayList<>();
    }

    public List<Slot> getActiveSlotList() {
        return activeSlotList;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
    public boolean isActive(){
        if(x == null || y == null || width == null || height == null){
            return  false;
        }
        return true;
    }
    public Slot findSlotInList(Slot slot){
        if(activeSlotList.isEmpty()){
            return null;
        }
        for(Slot slot2: activeSlotList){
            if(slot2.equals(slot)){
                return slot2;
            }
        }
        return  null;
    }
    public void removeAllFromList(){
        this.getActiveSlotList().clear();
    }
    public void addActiveSlot(Slot activeSlot){
        this.activeSlotList.add(activeSlot);
    }
}
