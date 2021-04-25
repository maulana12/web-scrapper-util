package Product.Handphone;


import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

public class HandphoneService {

    private static final String URLSTR = "https://www.tokopedia.com/search?st=product&q=";
    private static final String CATEGORY = "handphone";
    private static final String EMPTY = "";
    private static final String EXT = ".csv";
    private static final String AMPERSAND  = "&";
    private static final String XPATH_MERCHANT_NAME = "//*[@data-testid='llbPDPFooterShopName']//h2";

    private static final String STRSITE = "r=";

    private static final String TEMPLATE_URL_ADS = "https://ta.tokopedia.com/promo/";

    private static String[] header = {"Name", "Description", "Image Link", "Price", "Rating", "Name Merchant"};

    public List getListProductFromHTML() throws IOException {
        Document document = null;
        String itemType = CATEGORY;
        String category = itemType.replaceAll("\\s", "%20");
        String url = URLSTR + category;

        document = Jsoup.connect(url).timeout(60000).get();

        Elements elements = document.getElementsByClass("pcv3__container css-1bd8ct");

        List<HandphoneEntity> listHandphone = new ArrayList<HandphoneEntity>();


        for (Element e : elements) {
            BigDecimal price = BigDecimal.valueOf(0);
            String name = EMPTY;
            String description = EMPTY;
            String link = EMPTY;
            String pricesStr = EMPTY;
            String rating = EMPTY;
            String merchant = EMPTY;
            String pageLink = EMPTY;
            String linkFromAds= EMPTY;

            name = e.getElementsByClass("css-18c4yhp").text();

            //Element link = document.select("a").first();
            pageLink = e.getElementsByClass("css-1ehqh5q").select("a").attr("href");
            if (pageLink == null || pageLink.length() == 0) {
                throw new IllegalArgumentException("String must not be empty");
            }
            if (isURLAds(pageLink)) {
                linkFromAds = getLinkFromAds(pageLink);
                Document urlAdsDocument = Jsoup.connect(linkFromAds).timeout(60000).get();

                Elements elementsfromURLAds = urlAdsDocument.getElementsByClass("css-1vh65tm");

                for(Element e2: elementsfromURLAds)
                {
                    merchant = e2.getElementsByClass(" css-1nf4dbc").text();
                }
            }

            link = e.getElementsByClass("css-1ehqh5q").select("img").attr("src");

            pricesStr = String.valueOf((e.getElementsByClass("css-rhd610").text().substring(2).replace(".","")).trim());
            price = new BigDecimal(pricesStr);
            rating = e.getElementsByClass("css-etd83i").text() ;


            //Add Entity
                    HandphoneEntity handphoneEnt = new HandphoneEntity(name, description, link,price, rating,  merchant);
            listHandphone.add(handphoneEnt);
        }
        return listHandphone;
    }

    private boolean isURLAds(String path)
    {
        return path.contains(TEMPLATE_URL_ADS);
    }

    private String getLinkFromAds(String path) throws IOException {
        int x =path.indexOf(STRSITE);
        String subsURL = path.substring(x);
        String[] test = path.split(STRSITE);
        //String URL = subsURL.split(AMPERSAND)[0];
        String URL = test[1].split(AMPERSAND)[0];
        return URLDecoder.decode(URL);
    }

    public void writeCSVFile(List csvData, File path)  {
        long currentTime= Calendar.getInstance().getTimeInMillis();
        File file = new File(CATEGORY+"_"+currentTime+EXT);
        try {

            FileWriter outputfile = new FileWriter(path.getPath()+"\\"+file);
            //FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            List<String[]> listItems = new ArrayList<>();

            //Header
            listItems.add(header);
            for(int i =0; i<csvData.size(); i++)
            {
                HandphoneEntity entity = (HandphoneEntity) csvData.get(i);
                if(entity!=null) {
                    listItems.add(new String[]{entity.getName(), entity.getDescription(), entity.getLinkImage(), String.valueOf(entity.getPrice()), String.valueOf(entity.getRating()), entity.getMerchant()});
                }
            }
            writer.writeAll(listItems);
            writer.close();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

}
