package zk.kotu.com;

        import org.apache.zookeeper.CreateMode;
        import org.apache.zookeeper.KeeperException;
        import org.apache.zookeeper.ZooDefs;
        import org.apache.zookeeper.ZooKeeper;

        import java.io.IOException;
        import java.util.List;

/**
 * Created by kotu on 9/13/16.
 */
public class CreateZNode {

    private static ZooKeeper zk;
    private static ZkConnector zkc;
    public static void create(String path, byte[] data, ZooKeeper zk)  throws KeeperException, InterruptedException, IllegalStateException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    public static void main(String[] args) throws IOException, IllegalStateException, InterruptedException, KeeperException {
        String path = "/yamplus/bcp";
        byte[] data = "Sample znode data".getBytes();
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");
        create(path, data, zk);
        zk.close();
    }

}
