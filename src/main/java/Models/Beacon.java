package Models;

public class Beacon {
    private int id;
    private int tx_pow;

    public int getTx_pow() {
        return tx_pow;
    }

    private int rssi;
    private Double prevDistance;
    private int locationX;
    private int locationY;

    public Beacon(int locationX, int locationY, int tx_pow, int rssi) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.tx_pow = tx_pow;
        this.rssi = rssi;
    }

    public int GetRssi(Client client){
        var distance =
                Math.sqrt(Math.pow(client.getRealXLocation() - locationX,2) + Math.pow(client.getRealYLocation() - locationY,2));
        if (prevDistance == null){
            prevDistance = distance;
            return rssi;
        }
        if (prevDistance > distance){
            var diff = (prevDistance - distance) / 100;
            var rssiDiff = tx_pow * diff;
            rssi += rssiDiff;
            prevDistance = distance;
            return rssi;
        }
        else if (prevDistance == distance){
            return rssi;
        }
        var diff = (distance - prevDistance) / 100;
        var rssiDiff = tx_pow *diff;
        rssi -= rssiDiff;
        prevDistance = distance;
        return rssi;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }


}
