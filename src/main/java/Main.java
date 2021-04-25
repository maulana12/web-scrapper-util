
import Product.Handphone.HandphoneService;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);

      HandphoneService handphoneService = new HandphoneService();
        File path = f.getSelectedFile();
        System.out.println(path.getPath());
        List dataProduct = null;
        try {
            dataProduct = handphoneService.getListProductFromHTML();
        } catch (IOException e) {
            e.printStackTrace();
        }
        handphoneService.writeCSVFile(dataProduct,path);
    }
}
