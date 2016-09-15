package zk.kotu.com;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
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
        String path = "/test7";
        byte[] data = "i have been updated to asdas ".getBytes();
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");
        zk.exists(path, new Watcher());
        update(path,data);
        byte[] res = ReadZNode.read("/yamplus", zk);
        for (byte b : res) {
            System.out.print((char) b);
        }
    }
}
