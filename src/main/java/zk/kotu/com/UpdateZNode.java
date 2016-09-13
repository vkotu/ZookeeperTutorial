package zk.kotu.com;

import org.apache.zookeeper.ZooKeeper;

/**
 * Created by kotu on 9/13/16.
 */
public class UpdateZNode {

    private static ZooKeeper zk;
    private static ZkConnector zkc;

    public static void update(String path, byte[] data) throws Exception {
        zk.setData(path, data, zk.exists(path, true).getVersion());
    }

    public static void main(String[] args) throws Exception {
        String path = "/yamplus";
        byte[] data = "i have been updated".getBytes();
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");
        update(path,data);
        byte[] res = ReadZNode.read("/yamplus", zk);
        for (byte b : res) {
            System.out.print((char) b);
        }
    }
}
