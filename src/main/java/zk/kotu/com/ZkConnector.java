package zk.kotu.com;
;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


/**
 * Created by kotu on 9/13/16.
 */
public class ZkConnector {

    private ZooKeeper zk;
    private CountDownLatch connSignal = new CountDownLatch(1);

    public ZooKeeper connect(String host) throws IOException, InterruptedException, IllegalStateException {
        zk = new ZooKeeper(host, 5000, new Watcher() {
            public void process(WatchedEvent event){
                if(event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    connSignal.countDown();
                }
            }
        });
        connSignal.await();
        return zk;
    }

    public void close() throws InterruptedException {
        zk.close();
    }


}
