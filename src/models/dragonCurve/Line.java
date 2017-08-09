package models.dragonCurve;

public class Line {
    private int direction; // 1-up 2-right 3-down 4-left
    private int x;
    private int y;

    public Line(int direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCCWDirection() {
        switch (this.getDirection()) {
            case 1:
                return 4;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public int getCWDirection() {
        switch (this.getDirection()) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 1;
            default:
                return 0;
        }
    }

    public int generateNextX() {
        switch (this.getDirection()){
            case 1:
                return this.getX();
            case 2:
                return this.getX()+10;
            case 3:
                return this.getX();
            case 4:
                return this.getX()-10;
            default:
                return 0;
        }
    }

    public int generateNextY() {
        switch (this.getDirection()){
            case 1:
                return this.getY()-10;
            case 2:
                return this.getY();
            case 3:
                return this.getY()+10;
            case 4:
                return this.getY();
            default:
                return 0;
        }
    }
}
