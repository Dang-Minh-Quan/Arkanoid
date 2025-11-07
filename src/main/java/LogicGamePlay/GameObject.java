package LogicGamePlay;

public class GameObject {
    public Ball createBall(String type) {
        if (type.equals("normal ball")) {
            return new NormalBall();
        }
        return null;
    }

    public Paddle createPaddle(String type) {
        if (type.equals("normal paddle")) {
            return new LongPaddle();
        } else if (type.equals("long paddle")) {
            return new NormalPaddle();
        }
        return null;
    }

    public Brick createBrick(int row, int col, String type) {
        if (type.equals("normal brick")) {
            return new NormalBirck(row, col);
        } else if (type.equals("strong brick")) {
            return new StrongBirck(row, col);
        } else if (type.equals("unbreakable brick")) {
            return new UnbreakableBrick(row, col);
        } else if (type.equals("explosive brick")) {
            return new ExplosiveBrick(row, col);
        }
        return null;
    }
}
