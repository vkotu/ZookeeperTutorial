package zk.kotu.com;

        import lombok.extern.java.Log;
        import lombok.extern.slf4j.Slf4j;
        import org.apache.zookeeper.*;
        import org.apache.zookeeper.data.ACL;

        import java.io.IOException;
        import java.util.List;

/**
 * Created by kotu on 9/13/16.
 */
@Slf4j
public class CreateZNode {

    private static ZooKeeper zk;
    private static ZkConnector zkc;
    public static void create(String path, byte[] data, ZooKeeper zk)  throws Exception{
        String[] directories = path.split("/");
        StringBuilder currentPath = new StringBuilder("");
        for(String dir : directories) {
            if(!dir.isEmpty()) {
                currentPath.append("/"+dir);
                System.out.println("checking path" + currentPath.toString());
                if(zk.exists(currentPath.toString(), false) != null) {
                    continue;
                } else {
                    System.out.println("creating " + currentPath.toString());
                    zk.create(currentPath.toString(), data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            }
        }
    }
    public static String createNested(final String path, byte[] data, List<ACL> acl,
                               CreateMode createMode) throws KeeperException, InterruptedException {
//        log.debug("Create Nested Path: {}", path);

        int pos = path.indexOf("/", 1);
        for (; pos != -1; pos = path.indexOf("/", pos + 1)) {
            try {
                zk.create(path.substring(0, pos), data, acl, CreateMode.PERSISTENT);
            }
            catch (KeeperException.NodeExistsException e) {
//                log.debug("Znode " + path.substring(0, pos) + " already exists");
            }
        }
        return zk.create(path, data, acl, createMode);
    }
    public static void main(String[] args) throws Exception {
        final String path = "/test8";
        byte[] data = "Sample znode data".getBytes();
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");
        zk.exists(path, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                boolean isNodeCreated = watchedEvent.getType().equals(Event.EventType.NodeCreated);
                boolean isMyPath = watchedEvent.getPath().equals(path);
                if(isNodeCreated && isMyPath) {
                    log.info("Created a new node with path" + path);
                }
            }
        });
        try {
            createNested(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        zk.close();
    }

}
