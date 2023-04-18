package utils;

public class CustomMathUtils {
    public static double pythagoras(double a, double b){
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static boolean checkCircleOverlap(int x2, int x1, int y2, int y1){
        int centerLenght = (int) CustomMathUtils.pythagoras(x2 - x1, y2 - y1);

        // checking the overlap
        return centerLenght < 100 ? true : false;
    }
}
