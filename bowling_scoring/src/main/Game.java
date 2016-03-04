public class Game {

    public static void main(String[] args) {

        int[] scoreBoard = {3, 5, 5, 2, 1, 4, 0, 1, 4, 5};

        int rounds = 10;
        int maxScore = 5;
        int totalPoints = 0;

        System.out.println("The total score is : " + totalScore(scoreBoard, rounds, maxScore, totalPoints));

    }

    public static int totalScore(int[] scoreBoard, int rounds, int maxScore, int totalPoints) {

        for(int i = 0; i <= rounds - 1; i++) {
            if (i == rounds - 1){
                if (scoreBoard[9] == maxScore) {
                    totalPoints = totalPoints + 10;
                }
                else {
                    totalPoints = totalPoints + scoreBoard[i];
                }
            }
            else {
                if (scoreBoard[i] == maxScore) {
                    totalPoints = totalPoints + (scoreBoard[i] + scoreBoard[i + 1]);
                }
                else {
                    totalPoints = totalPoints + scoreBoard[i];
                }
            }
        }

        return totalPoints;
    }
}
