package zk.kotu.com;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by kotu on 9/15/16.
 */
@Data
@Slf4j
public class Test {
    int x;
    public void myFunc() {
        log.debug("this a log");
    }
}
