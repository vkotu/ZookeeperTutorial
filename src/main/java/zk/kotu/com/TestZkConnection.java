package zk.kotu.com;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kotu on 9/13/16.
 */
public class TestZkConnection {

    private static ZooKeeper zk;
    private static ZkConnector zkc;

    private static List<String> znodeList = new ArrayList<String>();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zkc = new ZkConnector();
        zk = zkc.connect("driftshift.corp.ne1.yahoo.com");
        znodeList = zk.getChildren("/yamplus", true);
        for (String znode : znodeList ) {
            System.out.println(znode);
        }
        zk.close();

    }

}
