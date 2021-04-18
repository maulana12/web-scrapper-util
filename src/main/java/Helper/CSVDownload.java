package Helper;

import Product.Handphone.HandphoneEntity;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CSVDownload {

    private static final String EXT = ".csv";
    private static final String CATEGORY = "HANDPHONE";
    private static String[] header = {"Name", "Description", "Image Link", "Price", "Rating", "Name Merchant"};

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
