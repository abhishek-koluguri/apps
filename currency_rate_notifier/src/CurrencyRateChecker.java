import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class CurrencyRateChecker extends TimerTask implements Subject {

    List<Observer> usersList = new ArrayList<Observer>();
    Map<String, Double> currencyMap = new HashMap<String, Double>();
    private static CurrencyRateChecker currencyRateChecker;

    @Override
    public void registerUser(Observer user) {
        usersList.add(user);
    }

    public static CurrencyRateChecker getInstance() {
        if (currencyRateChecker == null) {
            return new CurrencyRateChecker();
        }
        return currencyRateChecker;
    }

    @Override
    public void notifyUser() {
        //System.out.println("inside notify user");
        for (Observer user : usersList) {
            // System.out.println("inside for");
            user.update(null, this.currencyMap);
        }
    }

    @Override
    public void unregisterUser(Observer user) {
        usersList.remove(user);
    }

    @Override
    public void run() {
        try {
            Map<String, Double> currencyMap = new HashMap<String, Double>();
            URL url = new URL("http://rates.fxcm.com/RatesXML");
            URLConnection connection = url.openConnection();

            Document doc = parseXML(connection.getInputStream());
            connection.setConnectTimeout(3000);
            NodeList descNodes = doc.getElementsByTagName("Rate");
            for (int i = 0; i < descNodes.getLength(); i++) {
                currencyMap.put(
                        descNodes.item(i).getAttributes().item(0)
                                .getNodeValue(),
                        Double.parseDouble(descNodes.item(i).getChildNodes()
                                .item(1).getTextContent()));
				/*
				 * System.out.println(descNodes.item(i).getChildNodes().item(1).
				 * getTextContent());
				 * System.out.println(descNodes.item(i).getAttributes
				 * ().item(0).getNodeValue());
				 */
            }
            this.currencyMap = currencyMap;
            notifyUser();
        } catch (Exception e) {
        }
    }

    private Document parseXML(InputStream stream) throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }
}
