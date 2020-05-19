/**
 * @ClassName GenerateTest
 * @Authror zhouzhiqiang
 * @Date 2020/3/21 0:34
 * @description
 * @version 1.0
 */
package erp.utils;

import java.io.*;

public class GenerateTest {
    public static void main(String[] args) throws IOException {
        generateTest("OderModel");
    }
    public static void generateTest(String className) throws IOException {
        String locaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoTest.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("test/erp/service/serviceImpTest/"+className+"ServiceImpTest.java"));
        String lien=null;
        String newLien=null;
        while ((lien=br.readLine())!=null) {
            newLien=lien.replace("Demo",className).replace("demo",locaseClassName);
            bw.newLine();
            bw.write(newLien);
            bw.flush();
        }
        if (bw != null) {
            bw.close();
        }
        if (br != null) {
            br.close();
        }
    }
}
