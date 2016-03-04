/**
 * Created by Abhishek Koluguri on 10/5/2015.
 */
public class PatternMatching {// pattern: t1234#
    public boolean checkingPattern(String password){
        if(password.charAt(0) >= 65 && password.charAt(0) <= 122){
            for(int i = 1; i <= 4; i++){
                if(password.charAt(i) >= 0 && password.charAt(i) <= 9)
                    if(password.charAt(6) == '#')
                        return true;
            }

        }
        return false;
    }
}
