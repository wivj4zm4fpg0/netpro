package networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachabilityTest {
    private static final int TIMEOUT = 2000;

    public ReachabilityTest(String addressText) {
        try {
            long max = 0;
            long min = 0;
            long sum = 0;
            int num = 0;

            InetAddress address = InetAddress.getByName(addressText);
            System.out.println("Pinging " + address.getHostName() 
                               + " [" + address.getHostAddress() + "].\n");

            int i = 0;
            for (; i < 4; i++) {
                long start = System.currentTimeMillis();


                boolean isReachable = address.isReachable(TIMEOUT);
                
                long end = System.currentTimeMillis();
                long time = end - start;

                if (isReachable) {
                    System.out.print("Reply from " 
                                     + address.getHostAddress() + ": ");

                    sum += time;

                    if (i == 0) {
                        max = time;
                        min = time;
                    } else {
                        if (time > max) {
                            max = time;
                        } else if (time < min) {
                            min = time;
                        }
                    }
                    num++;

                    if (time > 0) {
                        System.out.println("time=" + time + "ms");
                    } else {
                        System.out.println("time<1ms");
                    }
                } else {
                    if (time >= TIMEOUT) {
                        System.out.println("Request timed out.");
                    } else {
                        System.out.println("Reply from " 
                                         + address.getHostAddress() 
                                         + ": Destination net unreachable.");
                        num++;
                    }
                }

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    break;
                }
            }

            System.out.println("\nPing statistics for " 
                               + address.getHostAddress() + ":");
            int lost;
            if (num == 0) {
                lost = 100;
            } else {
                lost = (i - num) * 100 / num;
            }

            System.out.println("    Packets: Sent = " + i 
                               + ", Received = " + num 
                               + ", Lost = " + (i-num) + " (" + lost + "% loss),");

            if (num != 0) {
                System.out.println("Approximate round trip times in milli-seconds:");

                long average;
                if (num != 0) {
                    average = sum / num;
                } else {
                    average = 0;
                }
                
                System.out.println("    Minimum = " + min 
                                   + "ms, Maximum = " + max 
                                   + "ms, Average = " + average + "ms");
            }

        } catch (UnknownHostException ex) {
            System.err.println("Unknown host " + addressText + ".");
        } catch (IOException ex) {
            System.err.println("Network Error Occurred.");
        } 
    }

    public static void main(String[] args) {
        new ReachabilityTest(args[0]);
    }
}
