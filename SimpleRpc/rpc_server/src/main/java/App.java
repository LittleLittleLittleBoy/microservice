import service.HelloService;
import service.impl.HelloServiceImpl;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Server server=new Server();
        server.register(HelloService.class,HelloServiceImpl.class);
        server.start(8020);
    }
}
