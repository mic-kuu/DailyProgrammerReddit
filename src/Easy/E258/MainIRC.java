package Easy.E258;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/4ad23z/20160314_challenge_258_easy_irc_making_a/
 */

import java.io.*;
import java.net.*;

public class MainIRC {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Wrong number of arguments! <hostname> <portnumber>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket ircSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(ircSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(ircSocket.getInputStream()))
        ) {
            out.print("PASS secret\r\n");
            out.print("NICK testowy\r\n");
            out.print("USERNAME tesUser 0 * :Maciek\r\n");

            while (true) {

                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O error for " +
                    hostName);
            System.exit(1);
        }
    }
}