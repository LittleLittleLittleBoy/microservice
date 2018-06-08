import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 使用动态代理的方式，根据提供的服务接口类序列化为码流，向目标服务器发送Socket远程调用，并把结果返回
 * @param <T>
 */
public class Client<T> {
    @SuppressWarnings("unchecked")
    public static <T> T get(final Class<?> serviceInterface, final InetSocketAddress addr){
        T instance=(T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket=null;
                        ObjectOutputStream output=null;
                        ObjectInputStream input=null;
                        try {
                            //连接服务器
                            socket=new Socket();
                            socket.connect(addr);
                            //将调用的接口类、方法名、参数列表等序列后发送给服务器提供者
                            output=new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceInterface.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);
                            //同步阻塞等待服务器返回应答，获取应答后返回
                            input=new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        }finally {
                            if (socket!=null) socket.close();
                            if (input!=null) input.close();
                            if (output!=null) output.close();
                        }
                    }
                });

        return instance;
    }
}
