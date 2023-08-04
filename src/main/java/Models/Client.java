package Models;

public class Client{
    private int realXLocation = 100;
    private int realYLocation = 100;
    private int predictedXLocation;
    private int predictedYLocation;


    public void moveHorizontal(int n){
        realXLocation += n;
    }
    public void moveVertical(int n){
        realYLocation += n;
    }

    public void GetResult(){
        System.out.println("Real Location : X = " + realXLocation+ ", Y = " + realYLocation);
        System.out.println("Predicted Location : X = " + predictedXLocation+ ", Y = " + predictedYLocation);
    }







    public int getRealXLocation() {
        return realXLocation;
    }

    public int getRealYLocation() {
        return realYLocation;
    }

    public int getPredictedXLocation() {
        return predictedXLocation;
    }

    public void setPredictedXLocation(int predictedXLocation) {
        this.predictedXLocation = predictedXLocation;
    }

    public int getPredictedYLocation() {
        return predictedYLocation;
    }

    public void setPredictedYLocation(int predictedYLocation) {
        this.predictedYLocation = predictedYLocation;
    }




}