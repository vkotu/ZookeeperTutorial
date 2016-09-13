package zk.kotu.com;

import org.apache.zookeeper.ZooKeeper;

/**
 * Created by kotu on 9/13/16.
 */
public class DeleteZnode {


    private static ZooKeeper zk;
    private static ZkConnector zkc;

    public static void delete(String path, ZooKeeper zk) throws Exception {
        zk.delete(path, zk.exists(path, true).getVersion());
    }

    public static void main(String[] args) throws Exception {
        String path = "/yamplus/bcp";
        zkc = new ZkConnector();
        zk = zkc.connect("localhost");

        System.out.println("Is node exists?" + zk.exists(path,true));

        delete(path, zk);

        System.out.println("Is node exists?" + zk.exists(path,true));
    }

}
