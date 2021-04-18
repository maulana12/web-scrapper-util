import Helper.CSVDownload;
import Product.Handphone.HandphoneImpl;

import java.util.List;

public class Main {
    private static CSVDownload csvDownload;

    public static void main(String[] args) throws Exception {

        csvDownload = new CSVDownload();
        HandphoneImpl handphoneImpl = new HandphoneImpl();
       List dataProduct =    handphoneImpl.getListProductFromHTML();
        csvDownload.writeCSVFile(dataProduct);


    }
}
