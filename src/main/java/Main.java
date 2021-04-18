
import Product.Handphone.HandphoneService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {


        HandphoneService handphoneService = new HandphoneService();
        List dataProduct = handphoneService.getListProductFromHTML();

        handphoneService.writeCSVFile(dataProduct);


    }
}
