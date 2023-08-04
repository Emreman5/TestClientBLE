package Models;


import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SenderSocket {
    private  String hostName;
    private  int portNumber;
    private  Socket socket;

    public SenderSocket(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        socket = CreateSocket();
    }
    public SenderSocket(){

    }

    private Socket CreateSocket() {
        Socket socket = null;
        try {
            socket = new Socket(hostName, portNumber);
        } catch (IOException e) {
            System.out.println("HOST BULUNAMADI");
            return null;
        }
        System.out.println("CONNECTED");
        return socket;
    }

    public void SendData(byte[] data) {
        try {
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            System.out.println(data.length);
            dout.writeInt(data.length);
            dout.write(data);;
        } catch (IOException e) {
            System.out.println("CONNECTION RESET");
        }
    }

    public byte[] CreateData(Beacon b1, Beacon b2, Beacon b3, Client client) {
        var arr = Arrays.asList(b1, b2, b3);
        var data = new byte[21];
        int arrCount = 0;
        for (int i = 0; i < data.length ; i+= data.length / 3) {
            int innerArrCount = 0;
            data[i] = (byte) arr.get(arrCount).getRssiAlt(client);
            var macAddressInBytes = arr.get(arrCount).getMacAddressInBytes();
            for (int j = i + 1; j < i + (data.length / 3) ; j++) {
                    data[j] = macAddressInBytes[innerArrCount];
                    innerArrCount++;
            }
            arrCount++;
        }
        var realLocationX = ByteBuffer.allocate(4).putInt(client.getRealXLocation()).array();
        var realLocationY = ByteBuffer.allocate(4).putInt(client.getRealYLocation()).array();
        byte[] realLocation = new byte[realLocationY.length + realLocationY.length];
        for (int i = 0; i < realLocation.length ; i++) {
            realLocation[i] = i < realLocationX.length ? realLocationX[i] : realLocationY[i - realLocationX.length];
        }
        var resultData = new byte[data.length + realLocation.length];
        var buf = ByteBuffer.wrap(resultData);
        buf.position(0);
        buf.put(data);
        buf.position(data.length);
        buf.put(realLocation);
        return resultData;
    }
}

