package Models;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SenderSocket {
    private final String hostName;
    private final int portNumber;
    private final Socket socket;

    public SenderSocket(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        socket = CreateSocket();
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
            dout.writeInt(data.length);
            dout.write(data);
        } catch (IOException e) {
            System.out.println("CONNECTION RESET");
        }
    }

    public byte[] CreateData(Beacon b1, Beacon b2, Beacon b3, Client client) {
        var arr = Arrays.asList(b1, b2, b3);
        var data = new byte[6];
        for (int i = 0; i < arr.toArray().length *2 - 1; i+=2) {
            data[i] = (byte) arr.get(i / 2).getTx_pow();
            data[i+1] = (byte) arr.get(i / 2).GetRssi(client);
        }
        return data;
    }
}

