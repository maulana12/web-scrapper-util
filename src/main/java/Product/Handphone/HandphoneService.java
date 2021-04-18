package Product.Handphone;


import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class HandphoneService {

    private static final String URLSTR = "https://www.tokopedia.com/search?st=product&q=";
    private static final String CATEGORY = "handphone";
    private static final String EMPTY = "";
    private static final String EXT = ".csv";

    private static String[] header = {"Name", "Description", "Image Link", "Price", "Rating", "Name Merchant"};

    public List getListProductFromHTML() throws IOException {
        Document document = null;
        String itemType = CATEGORY;
        String category = itemType.replaceAll("\\s","%20");
        String url =  URLSTR+category;

        document = Jsoup.connect(url).get();

        Elements elements = document.getElementsByClass("pcv3__container css-1bd8ct");
        List<HandphoneEntity> listHandphone = new ArrayList<HandphoneEntity>();

        BigDecimal price= BigDecimal.valueOf(0);
        String name = EMPTY;
        String description= EMPTY;
        String link= EMPTY;
        String pricesStr= EMPTY;
        String rating= EMPTY;
        String merchant = EMPTY;

        for(Element e:elements){

            name = e.getElementsByClass("css-18c4yhp").text();

            description = "asd";

            link = e.getElementsByClass("css-1ehqh5q").select("img").attr("src");
            pricesStr = String.valueOf((e.getElementsByClass("css-rhd610").text().substring(2).replace(".","")).trim());
            price = new BigDecimal(pricesStr);
            System.out.println(pricesStr);
            rating = e.getElementsByClass("css-etd83i").text() ;
            System.out.println(e.getAllElements());

            merchant = e.getElementsByClass("css-1pznt2j").text();
            //Add Entity
            HandphoneEntity handphoneEnt = new HandphoneEntity(name, description, link,price, rating,  "Store");
            listHandphone.add(handphoneEnt);
        }


        return listHandphone;
    }

    public void writeCSVFile(List csvData)  {
        long currentTime= Calendar.getInstance().getTimeInMillis();
        System.out.println(currentTime);

        File file = new File(CATEGORY+"_"+currentTime+EXT);
        try {

            FileWriter outputfile = new FileWriter(file);
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
