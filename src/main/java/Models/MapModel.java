package Models;

class MapModel{
    private int id;
    private int width;
    private int height;

    public MapModel(int id,int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}