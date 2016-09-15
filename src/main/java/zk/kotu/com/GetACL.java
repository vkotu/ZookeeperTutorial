package zk.kotu.com;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.util.List;

/**
 * Created by kotu on 9/14/16.
 */
public class GetACL {

    private static ZooKeeper zk;
    private static ZkConnector zkc;

    private static List<ACL> getAcl(String path) throws Exception {
        return zk.getACL(path, zk.exists(path, true));
    }

    public static void main(String[] args) throws  Exception{
        String path = "/getmyacl";
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");
        List<ACL> acl = getAcl(path);
        for(ACL aclItem : acl) {
            System.out.println(aclItem.toString());
        }
    }


}
