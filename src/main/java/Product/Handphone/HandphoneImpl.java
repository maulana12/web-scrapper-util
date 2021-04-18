package Product.Handphone;

import Helper.CSVDownload;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.codec.binary.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class HandphoneImpl {

    private static final String URLSTR = "https://www.tokopedia.com/search?st=product&q=";
    private static final String CATEGORY = "handphone";
    private static final String EMPTY = "";

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
}
