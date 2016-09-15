package zk.kotu.com;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;

/**
 * Created by kotu on 9/15/16.
 */
@Slf4j
class Watcher implements org.apache.zookeeper.Watcher {
    public void process(WatchedEvent watchedEvent) {
        boolean isNodeCreated = watchedEvent.getType().equals(Event.EventType.NodeCreated);
        boolean isNodeUpdated = watchedEvent.getType().equals(Event.EventType.NodeDataChanged);
        boolean isNodeDeleted = watchedEvent.getType().equals(Event.EventType.NodeDeleted);
//        boolean isMyPath = watchedEvent.getPath().equals(path);
        if(isNodeCreated) {
            log.info("Created a new node with path" + watchedEvent.getPath());
        }
        if(isNodeUpdated) {
            log.info("Node is updated in the path" + watchedEvent.getPath());
        }
        if(isNodeDeleted) {
            log.info("Node is deleted" + watchedEvent.getPath());
        }

    }
}
