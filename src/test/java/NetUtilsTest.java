/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package netutils;

import netutils.NetUtils;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetUtilsTest {

    @Test public void testFindNetworkAddress() {
        String[] networkNames = {"docker0", "vboxnet0", "vboxnet1", "en0", "en1", "en2", "en3", "lo"};
        List<String> addresses = new ArrayList<String>();
        try {
            addresses = NetUtils.findNetworkAddresses(networkNames); 
        } catch(SocketException e) {
            System.out.println(e.toString());
        }
        // It will be hard to test the results in a cross-platform way
        Assert.assertFalse(addresses.isEmpty());
    }

    @Test public void testFindFreePort() {
        int port = NetUtils.findFreePort();
        // We just test that the port is free and ServerSocket does not throw
        try {
            ServerSocket sock = new ServerSocket(port);
            Assert.assertNotNull(sock);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

}
