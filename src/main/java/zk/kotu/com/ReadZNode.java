package zk.kotu.com;

import org.apache.zookeeper.ZooKeeper;

/**
 * Created by kotu on 9/13/16.
 */
public class ReadZNode {

    private static ZooKeeper zk;
    private static ZkConnector zkc;

    public static byte[] read(String path, ZooKeeper zk) throws Exception {
        return zk.getData(path, true, zk.exists(path, true));
    }

    public static void main(String[] args) throws Exception {
        String path = "/getmyacl";
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");
        String user = "vicky";
        String pwd = "password";
        zk.addAuthInfo("digest", (user + ":" + pwd).getBytes());

        byte[] data = read(path, zk);
        for (byte b : data) {
            System.out.print((char) b);
        }

    }
}
