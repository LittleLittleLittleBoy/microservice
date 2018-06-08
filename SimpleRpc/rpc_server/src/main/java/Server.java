import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ExecutorService executorService=Executors.newFixedThreadPool(10);

    private static final HashMap<String,Class> serviceRegistry=new HashMap<String, Class>();

    //提供一个数组保存所注册的服务接及实现类
    public void register(Class serviceInterface,Class impl){
        //注册服务
        serviceRegistry.put(serviceInterface.getName(),impl);
    }

    // 启动一个堵塞式的Socket服务用于等待客户端发起的调用请求,
    // 收到请求后将码流反序列化成对象根据接口从注册表中寻找具体实现累，
    // 最后根据反射方式调用该实现类返回结果
    public void start(int port) throws IOException{
        final ServerSocket server=new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("Service start");
        while (true){
            executorService.execute(new Runnable() {
                public void run() {
                    Socket socket=null;
                    ObjectInputStream input=null;
                    ObjectOutputStream output=null;
                    try {
                        socket=server.accept();
                        //接收到服务器调用请求,将码流反序列化定位具体服务
                        input=new ObjectInputStream(socket.getInputStream());
                        String serviceName=input.readUTF();
                        String methodName=input.readUTF();
                        Class<?>[] parameterTypes= (Class<?>[]) input.readObject();
                        Object[] arguments= (Object[]) input.readObject();
                        //在服务注册表中根据调用的服务获取到具体的实现类
                        Class serviceClass=serviceRegistry.get(serviceName);
                        if (serviceClass==null){
                            throw new ClassNotFoundException(serviceName+" service not found");
                        }

                        Method method=serviceClass.getMethod(methodName,parameterTypes);

                        //调用获取结果
                        Object result=method.invoke(serviceClass.newInstance(),arguments);
                        //将结果序列化后发送给客户端
                        output=new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if (socket!=null){
                                socket.close();
                            }
                            if (input!=null){
                                input.close();
                            }
                            if (output!=null){
                                output.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
