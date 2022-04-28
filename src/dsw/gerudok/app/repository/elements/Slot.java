package dsw.gerudok.app.repository.elements;

import dsw.gerudok.app.repository.node.RuNode;

public  class Slot extends RuNode {

    private int positionX;
    private int positionY;
    private int height;
    private int width;
    private Type type;
    private double angle;
    private String slotFilePath = "";
    private int fileType = -1;

    public Slot(String name, RuNode parent, int positionX, int positionY, int height, int width) {
        super(name, parent);
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
        this.angle = 0;
    }

    public Slot(String name, RuNode parent) {
        super(name, parent);
    }

    public Slot() {
    }


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public String getSlotFilePath() {
        return slotFilePath;
    }

    public void setSlotFilePath(String slotFilePath) {
        this.slotFilePath = slotFilePath;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public void changedParameters(){
        notified(this,"Promena parametara");
    }

}
