import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer {
    static int id_value;
    int id;

    public int getId() {
        return id;
    }

    Map<String, Double> target;
    Map<String, Integer> notificationCount;
    Notification notification;
    Map<String, Integer> low_high;
    Map<String, Integer> notification_Type;

    public User() {
        id_value = id_value + 1;
        id = id_value;
        target = new HashMap<String, Double>();
        notificationCount = new HashMap<String, Integer>();
        low_high = new HashMap<String, Integer>();
        notification_Type = new HashMap<String, Integer>();
    }

    public Map<String, Double> getTarget() {
        return target;
    }

    public void add(String currencyPair, double target, int low_high, int type) {
        this.target.put(currencyPair, new Double(target));
        this.notificationCount.remove(currencyPair);
        this.low_high.put(currencyPair, low_high);
        this.notification_Type.put(currencyPair, type);
    }

    public void listMyCurrencies() {
        Iterator it = target.entrySet().iterator();
        System.out.println("Your selected list of currency pairs are below");
        while (it.hasNext()) {
            Map.Entry<String, Double> pair_target = (Map.Entry<String, Double>) it
                    .next();
            System.out.println(pair_target.getKey() + " "
                    + pair_target.getValue() + " ");
        }
    }

    @Override
    public void update(Observable o, Object currencyMap) {
        Map<String, Double> cmap = (Map<String, Double>) currencyMap;
        Iterator it = target.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> pair_target = (Map.Entry<String, Double>) it
                    .next();
            if (cmap.containsKey(pair_target.getKey())) {
                if (notificationCount.containsKey(pair_target.getKey())) {
                    continue;
                }
                notificationCount.put(pair_target.getKey(), 1);
                if (low_high.get(pair_target.getKey()) == 1) {
                    if (pair_target.getValue() <= cmap
                            .get(pair_target.getKey())) {
                        if (notification_Type.get(pair_target.getKey()) == 1)
                            notification = new Message();
                        else
                            notification = new EMail();
                        notification.update(pair_target.getKey(),
                                pair_target.getValue(),
                                cmap.get(pair_target.getKey()), id);
                    }
                } else if (low_high.get(pair_target.getKey()) == 2) {
                    if (pair_target.getValue() >= cmap
                            .get(pair_target.getKey())) {
                        if (notification_Type.get(pair_target.getKey()) == 1)
                            notification = new Message();
                        else
                            notification = new EMail();
                        notification.update(pair_target.getKey(),
                                pair_target.getValue(),
                                cmap.get(pair_target.getKey()), id);
                    }
                }
            }
        }
    }
}