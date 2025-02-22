public class GaussSystem {
    private double[] system; //массив результатов
    private double determinant; //определитель
    private boolean singular; //вырождена\невырождена

    GaussSystem(double[] sys, double det, boolean sing){
        system = sys;
        determinant = det;
        singular = sing;
    }

    public double[] getSystem() {
        return system;
    }

    public double getDeterminant() {
        return determinant;
    }

    public boolean isSingular(){
        return singular;
    }
}
