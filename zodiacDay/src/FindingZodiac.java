import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class FindingZodiac {

    public boolean validate(String inputDate){

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date testDate = null;

        try{
            testDate = df.parse(inputDate);
        }
        catch (ParseException e){
            // there should be no print statements in any class except Driver class
        }

        if (!df.format(testDate).equals(inputDate)){
            return false;
        } else {
            return true;
        }
    }

    public String dayOfTheWeek(int month, int day, int year){

        Date date= (new GregorianCalendar(year, month-1, day)).getTime();
        SimpleDateFormat formattedDate = new SimpleDateFormat("EE");
        String dayOfWeek = formattedDate.format(date);
        return dayOfWeek;
    }

    public String zodiacSign(int date, int month){

        String[] zodiacSigns = new String[] {
                "Capricorn","Aquarius","Pisces","Aries","Taurus","Gemini",
                "Cancer","Leo","Virgo","Libra",
                "Scorpio","Sagittarius","none"
        };

        if((month == 11) && ( date>= 22) || (month == 0) && (date <= 19)) {
            return zodiacSigns[0];
        }else if((month == 0) && (date >= 20)  || (month == 1) && (date <= 18)) {
            return zodiacSigns[1];
        } else if((month == 1) && (date >= 19)  || (month == 2) && (date <= 20)) {
            return zodiacSigns[2];
        } else if((month == 2) && (date >= 21)  || (month == 3) && (date <= 19)) {
            return zodiacSigns[3];
        } else if((month == 3) && (date >= 20)  || (month == 4) && (date <= 20)) {
            return zodiacSigns[4];
        } else if((month == 4) && (date >= 21)  || (month == 5) && (date <= 20)) {
            return zodiacSigns[5];
        } else if((month == 5) && (date >= 21)  || (month == 6) && (date <= 22)) {
            return zodiacSigns[6];
        } else if((month == 6) && (date >= 23)  || (month == 7) && (date <= 22)) {
            return zodiacSigns[7];
        } else if((month == 7) && (date >= 23)  || (month == 8) && (date <= 21)) {
            return zodiacSigns[8];
        } else if((month == 8) && (date >= 22) || (month == 9) && (date <= 21)) {
            return zodiacSigns[9];
        } else if((month == 9) && (date >= 22)  || (month == 10) && (date <= 22)) {
            return zodiacSigns[10];
        } else if((month == 10) && (date >= 23)  || (month == 11) && (date <= 21)) {
            return zodiacSigns[11];
        } else {
            return null;
        }
    }
}