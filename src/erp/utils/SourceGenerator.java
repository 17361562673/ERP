/**
 * @ClassName SourceGenerator
 * @Authror zhouzhiqiang
 * @Date 2020/3/20 22:13
 * @description
 * @version 1.0
 */
package erp.utils;

import java.io.*;

public class SourceGenerator {
    public static void main(String[] args) throws IOException {
    }

    public static void generateJAVA(String className) throws IOException {
        generateQuery(className);
        generateDao(className);
        generateDaoImp(className);
        generateService(className);
        generateServiceImp(className);
    }

    /**
     * @Author zhouzhiqiang
     * @Description  生成query的代码
     * @Date 22:39 2020/3/20
     * @Param 
     * @return
     **/
    public static void generateQuery(String className) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoQuery.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/erp/query/"+className+"Query.java"));
        String lien=null;
        String newLien=null;
        while ((lien=br.readLine())!=null) {
            newLien=lien.replace("Demo",className);
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
    /**
     * @Author zhouzhiqiang
     * @Description 生成dao的代码
     * @Date 22:40 2020/3/20
     * @Param
     * @return
     **/
    public static void generateDao(String className) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoDao.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/erp/dao/"+className+"Dao.java"));
        String lien=null;
        String newLien=null;
        while ((lien=br.readLine())!=null) {
            newLien=lien.replace("Demo",className);
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
    /**
     * @Author zhouzhiqiang
     * @Description 生成daoImp的代码
     * @Date 22:44 2020/3/20
     * @Param
     * @return
     **/
    public static void generateDaoImp(String className) throws IOException {
        String locaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoDaoImp.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/erp/dao/daoImp/"+className+"DaoImp.java"));
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
    /**
     * @Author zhouzhiqiang
     * @Description 生成service的代码
     * @Date 23:02 2020/3/20
     * @Param
     * @return
     **/
    public static void generateService(String className) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoService.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/erp/service/"+className+"Service.java"));
        String lien=null;
        String newLien=null;
        while ((lien=br.readLine())!=null) {
            newLien=lien.replace("Demo",className);
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
    /**
     * @Author zhouzhiqiang
     * @Description 生成serviceImp的代码
     * @Date 23:10 2020/3/20
     * @Param
     * @return
     **/
    public static void generateServiceImp(String className) throws IOException {
        String locaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
        BufferedReader br = new BufferedReader(new FileReader("src/configure/template/DemoServiceImp.tlf"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/erp/service/serviceImp/"+className+"ServiceImp.java"));
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
