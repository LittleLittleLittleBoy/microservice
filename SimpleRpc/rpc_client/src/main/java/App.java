import service.HelloService;

import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) {
        HelloService service=Client.get(HelloService.class,new InetSocketAddress("localhost",8020));
        System.out.println("client:"+service.hello("candleflame"));
    }
}
