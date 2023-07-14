import Models.Beacon;
import Models.Client;
import Models.SenderSocket;

import java.text.MessageFormat;
import java.util.*;

public class Launch {
    public static void main(String[] args) {
        clientReq();
    }

    private static void testConsole() {
        Client client;
        client = new Client();
        var b1 = new Beacon(0, 0, 30, 45);
        var b2 = new Beacon(500, 500, 35, 35);
        var b3 = new Beacon(1000, 0, 40, 55);
        b1.setMacAddress("00:2B:67:C7:20:A6");
        b2.setMacAddress("00:2B:67:C7:20:A6");
        b3.setMacAddress("00:2B:67:C7:20:A6");
        var senderSocket = new SenderSocket();
        var data = senderSocket.CreateData(b1, b2, b3, client);
        List<String> parts = new ArrayList<String >();
        for (int i = 1; i < 7; i++) {
            parts.add(String.format("%02x", data[i]));

        }
        System.out.println(String.join(":", parts).toUpperCase());



            /*var i = new Scanner(System.in);
            var a = new Random();
            var xVar = 0;
            var yVar = 0;
            while (i.nextInt() == 1) {
                xVar = a.nextInt(-20, 20);
                yVar = a.nextInt(-20, 20);
                var msg1 = MessageFormat.format("Real Location X = {0}, Real Location Y = {1}", client.getRealXLocation()
                        , client.getRealYLocation());
                var msg2 = MessageFormat.format("Hareket edilen mesafe X = {0}, Y = {1}", xVar, yVar);
                var msg3 = MessageFormat.format("Beacon 1 Rssi : {0}", b1.GetRssi(client));
                var msg4 = MessageFormat.format("Beacon 2 Rssi : {0}", b2.GetRssi(client));
                var msg5 = MessageFormat.format("Beacon 3 Rssi : {0}", b3.GetRssi(client));
                System.out.println(msg1);
                System.out.println(msg2);
                System.out.println(msg3);
                System.out.println(msg4);
                System.out.println(msg5);
                client.moveHorizontal(xVar);
                client.moveVertical(yVar);*/
        //}
    }

    private static void clientReq() {
        var client = new Client();
        var b1 = new Beacon(0, 0, 30, 45);
        var b2 = new Beacon(100, 200, 35, 35);
        var b3 = new Beacon(200, 0, 40, 55);
        b1.setMacAddress("00:2B:67:C7:20:A6");
        b2.setMacAddress("02:50:F2:70:83:00");
        b3.setMacAddress("DE:3A:45:2D:48:1F");
        var requestSocket = new SenderSocket("127.0.0.1", 30);
        var i = new Scanner(System.in);
        var rnd = new Random();
        var xVar = 0;
        var yVar = 0;
        int count = 0;
        var data = new byte[6];
        while (i.nextInt() == 1) {
            System.out.println(MessageFormat.format("Real Location X = {0}, Real Location Y = {1}",
                    client.getRealXLocation()
                    , client.getRealYLocation()));
            data = requestSocket.CreateData(b1, b2, b3, client);
            requestSocket.SendData(data);
            System.out.println(MessageFormat.format("Hareket edilen mesafe X = {0}, Y = {1}", xVar, yVar));
            System.out.println(MessageFormat.format("Ölçülen rssi değerleri Count = {3} \nB1 = {0}\nB2 = {1}\nB3 " +
                            "= " +
                            "{2}",
                    b1.GetRssi(client), b2.GetRssi(client), b3.GetRssi(client), ++count));
            xVar = rnd.nextInt(-100, 100);
            yVar = rnd.nextInt(-100, 100);
            client.moveHorizontal(xVar);
            client.moveVertical(yVar);

        }
    }

}