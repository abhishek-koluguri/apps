import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class CurrencyRateNotifier {
    public static void main(String[] args) throws IOException {
        String input = "Y";
        String currencypair;
        boolean is_valid_rate, is_valid_low_high = false, is_valid_type = false;
        double rate = 0;
        int type = 0, low_high = 0;
        User user = null;
        CurrencyRateChecker cr = CurrencyRateChecker.getInstance();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(cr, 30, 30000);
        System.out.println("Currency Rate Notifier");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
                System.in));
        Map<Integer, User> users = new HashMap<Integer, User>();
        do {
            try {
                System.out.println("\n  SELECT AN OPTION : ");
                System.out.println("1.REGISTER NEW USER");
                System.out.println("2.SIGN IN");
                int option = Integer.parseInt(bufferReader.readLine());
                if (option == 1) {
                    user = new User();
                    System.out.println("Your user id is : " + user.getId()
                            + " Please use this to sign in\n");
                    users.put(user.getId(), user);
                    cr.registerUser(user);
                } else if (option == 2) {
                    System.out.println("Enter your id : ");
                    int id = Integer.parseInt(bufferReader.readLine());
                    if (users.containsKey(id)) {
                        user = users.get(id);
                        user.listMyCurrencies();
                    } else {
                        System.out
                                .println("Sorry no user exists with the given id");
                        continue;
                    }
                } else {
                    System.out
                            .println("Sorry Invalid option. Please select again.");
                    continue;
                }
                while (input.equals("Y") || input.equals("y")) {
                    System.out
                            .println("Please enter the currency pair for which you want to be notified:");
                    currencypair = bufferReader.readLine().toUpperCase();
                    if (cr.currencyMap.containsKey(currencypair)) {
                        do {
                            System.out
                                    .println("At what rate you want to be notified?");
                            try {
                                rate = Double.parseDouble(bufferReader
                                        .readLine());
                                is_valid_rate = true;
                            } catch (Exception e) {
                                System.out
                                        .println("Sorry invalid rate entered. Please select again.");
                                is_valid_rate = false;
                            }
                        } while (!is_valid_rate);
                        do {
                            System.out
                                    .println("When do you want to be notified");
                            System.out
                                    .println("1. \"Greater than or Equal to\" the target price");
                            System.out
                                    .println("2. \"Lower than\" the target price");
                            try {
                                low_high = Integer.parseInt(bufferReader
                                        .readLine());
                                if (!(low_high == 1 || low_high == 2))
                                    System.out
                                            .println("Sorry invalid option. Please select again.");
                                else
                                    is_valid_low_high = true;
                            } catch (Exception e) {
                                System.out
                                        .println("Sorry invalid option. Please select again.");
                                is_valid_low_high = false;
                            }

                        } while (!(((low_high == 1 || low_high == 2)) && is_valid_low_high));
                        do {
                            System.out
                                    .println("Please select the method by which you want to be notified");
                            System.out.println("1. Message");
                            System.out.println("2. E-Mail");
                            try {
                                type = Integer
                                        .parseInt(bufferReader.readLine());
                                if (!(type == 1 || type == 2))
                                    System.out
                                            .println("Sorry invalid option. Please select again.");
                                else
                                    is_valid_type = true;
                            } catch (Exception e) {
                                System.out
                                        .println("Sorry invalid option. Please select again.");
                                is_valid_type = false;
                            }
                        } while (!(((type == 1 || type == 2)) && is_valid_type));
                        user.add(currencypair, rate, low_high, type);
                        System.out
                                .println("Do you want to enter another currency pair");
                        input = bufferReader.readLine().trim();
                    } else {
                        System.out
                                .println("Sorry the entered currency pair doesn't exist in the system.\n");
                        continue;
                    }
                    type = low_high = 0;
                    is_valid_low_high = is_valid_type = false;
                }
                input = "Y";
            } catch (Exception e) {
                System.out
                        .println("Sorry Invalid option. Please select again.");
                continue;
            }
        } while (true);
    }
}