package cn.yoyo.components;

import cn.yoyo.components.security.OpenSSL;
import org.junit.jupiter.api.Test;

public class OpenSSLTest {
    @Test
    void test() throws Exception {
        String xx = OpenSSL.encrypt("18696183360", "T9DbDbFK", "Pthm9VRxPthm9VRx");
        System.out.printf("xx: %s\n", xx);
        xx = "yXSIBUzupCDaRMIzKHxGow==";
        xx = OpenSSL.decrypt(xx, "T9DbDbFK", "Pthm9VRxPthm9VRx");
        System.out.printf("xx: %s\n", xx);
    }
}
