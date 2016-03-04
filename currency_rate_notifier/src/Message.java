public class Message implements Notification {

    @Override
    public void update(String currency, Double value, Double currentPrice,
                       int id) {
        System.out.println("\n********Message**********");
        System.out.println("Request to notify for User by UserID: " + id + " "
                + currency + " " + value);
        System.out.println("Current currency value is " + currentPrice);
    }

}
