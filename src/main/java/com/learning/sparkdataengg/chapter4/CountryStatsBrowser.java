package com.learning.sparkdataengg.chapter4;

import picocli.CommandLine;
import picocli.CommandLine.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Iterator;
import java.util.Set;

@Command(name = "CountryStatsBrowser", version = "CountryStatsBrowser 1.0", mixinStandardHelpOptions = true)
public class CountryStatsBrowser implements Runnable {

    @Option(names = {"-H", "--host"}, description = "Jedis Host")
    private final String localhost = "localhost";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CountryStatsBrowser()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

        try (Jedis jedis =new Jedis(localhost)) {

            String lbKey = "country-stats";

            while (true) {

                //Query the leaderboard and print the results
                Set<Tuple> scores =
                        jedis.zrevrangeWithScores(
                                lbKey, 0, -1);

                Iterator<Tuple> iScores = scores.iterator();
                int position = 1;

                while (iScores.hasNext()) {
                    Tuple score = iScores.next();
                    System.out.println(
                            "Country Stats - " + position + " : "
                                    + score.getElement() + " = " + score.getScore());
                    position++;
                }
                System.out.println("-------------------------------------------------------");

                Thread.sleep(5000);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
