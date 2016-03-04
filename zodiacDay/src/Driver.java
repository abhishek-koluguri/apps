import java.util.Scanner;

/**
 * Created by Abhishek Koluguri on 9/25/2015.
 */
public class Driver {
    public static void main(String[] args) {

        FindingZodiac findingZodiac = new FindingZodiac();

        int date;
        int month;
        int year;

        System.out.println("Enter Date of Birth: ");
        Scanner input = new Scanner(System.in);
        String inputDate = input.nextLine();

        if(findingZodiac.validate(inputDate)) {

            String[] tokens = inputDate.split("/");

            date = Integer.parseInt(tokens[0]);
            month = Integer.parseInt(tokens[1]);
            year = Integer.parseInt(tokens[2]);

            String day = findingZodiac.dayOfTheWeek(month, date, year);
            String sign = findingZodiac.zodiacSign(date, month);

            System.out.println("Day of the week: " + day);
            System.out.println("Zodiac Sign: " + sign);
        }
        else {
            System.out.println("invalid date!!");
        }
    }
}
