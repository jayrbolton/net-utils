package us.kbase.netutils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Utilities for finding network addresses and free ports
 */
public class NetUtils {

    /**
     * IP address regexes used in findNetworkAddresses
     */
    private static final Pattern IPADDRESS_PATTERN = Pattern.compile(
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    /**
     * Find available networks based on a set of names.
     * TODO what is this
     */
    public static List<String> findNetworkAddresses(final String[] networkNames)
            throws SocketException {
        Set<String> networkNameSet = new HashSet<String>(Arrays.asList(networkNames));
        List<String> found = new ArrayList<String>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        // Iterate over every available network and see if they exist in networkNames
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            String name = iface.getName();
            String dispName = iface.getDisplayName();
            if (networkNameSet.contains(name) || networkNameSet.contains(dispName)) {
                Enumeration<InetAddress> ipAddresses = iface.getInetAddresses();
                while (ipAddresses.hasMoreElements()) {
                    String ip = ipAddresses.nextElement().getHostAddress();
                    if (IPADDRESS_PATTERN.matcher(ip).matches()) {
                        found.add(ip);
                    }
                }
            }
        }
        return found;
    }

    /**
     * Find a free and available port for launching a new server.
     */
    public static int findFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        } catch (IOException e) {}
        throw new IllegalStateException("Can not find available port in the system");
    }
}
