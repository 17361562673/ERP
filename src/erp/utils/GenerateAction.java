/**
 * @ClassName GenerateAction
 * @Authror zhouzhiqiang
 * @Date 2020/3/28 1:58
 * @description
 * @version 1.0
 */
package erp.utils;

import java.io.*;

public class GenerateAction {
    public static void main(String[] args) throws IOException {
    }



    /**
     * @Author zhouzhiqiang
     * @Description  生成action的方法
     * @Date 2:00 2020/3/28
     * @Param
     * @return
     **/
    public static void generateAction(String className) throws IOException {
        String locaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoAction.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/erp/controller/"+className+"Action.java"));
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
