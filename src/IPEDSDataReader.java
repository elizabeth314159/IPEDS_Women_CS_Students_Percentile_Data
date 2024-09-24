import java.util.*;
import java.io.*;

public class IPEDSDataReader {
    static HashMap<String, Double> percentWomen;
    static HashMap<String, Double> numWomen;
    static ArrayList<String> csPercent;

    public void work(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println(e + "uh oh");
        }
        percentWomen = new HashMap<String, Double>();
        numWomen = new HashMap<String, Double>();
        csPercent = new ArrayList<String>();
        String s = "";
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();
            String name = st.nextToken();
            String temp = st.nextToken();
            while (!(temp.charAt(0) >= '0' && temp.charAt(0) <= '9')) {
                name = name + " " + temp;
                temp = st.nextToken();
            }
            double satMath = Double.parseDouble(temp);
            double admitRate = Double.parseDouble(st.nextToken());
            double numStudents = Double.parseDouble(st.nextToken());
            double numCSStudents = Double.parseDouble(st.nextToken());
            double numCsWomen = Double.parseDouble(st.nextToken());
            percentWomen.put(name, numCsWomen / numCSStudents * 100);
            numWomen.put(name, numCsWomen);
            if (numCsWomen >= 20) {
                search(name, (numCsWomen / numCSStudents * 100));
            }

            System.out.println("testing");
        }
        int i = 1;
        for (String str : csPercent) {
            System.out.println(
                    i + " " + str + "      num women: " + numWomen.get(str) + "         % cs women: " + percentWomen.get(str));
            i++;
        }
    }

    public static void search(String name, double perw) {
        int x = 0;
        while (x < csPercent.size() && percentWomen.get(csPercent.get(x)) - perw > 0.001) {
            x++;
        }
        csPercent.add(x, name);
    }
}
