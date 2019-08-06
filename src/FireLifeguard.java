import java.io.*;
public class FireLifeguard {
    public static void main(String args[]) throws IOException {
        for (int i = 1 ; i < 7; ++i) {
            fire(i);
        }
    }

    private static void fire(int index) throws IOException {
        File data = new File("src/"+ index + ".txt");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(data));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        int totalLifeguards = Integer.parseInt(br.readLine());
        int[] start = new int[totalLifeguards];
        int[] end = new int[totalLifeguards];

        try {

            for (int i = 0; i < totalLifeguards; i++) {
                String temp = br.readLine();
                String[] line = temp.split(" ");
                start[i] = Integer.parseInt(line[0]);
                end[i] = Integer.parseInt(line[1]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxTime = fireLifeguard(start, end, totalLifeguards);
        System.out.println(maxTime);
        File output = new File("src/"+ index +".out");
        writeFile(output, maxTime);
    }

    private static void writeFile(File output, int maxTime) throws IOException {
        BufferedWriter bw=null;
        try {
            bw= new BufferedWriter(new FileWriter(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw.write(String.valueOf(maxTime));
        bw.close();
    }

    public static int fireLifeguard(int[] start, int[]end, int totalLifeguards) {
        int maxTime = 0;
        int[] coverInterval = new int[1000000000];

        for(int i = 0; i < totalLifeguards; i++)
        {
            for(int j = start[i]; j < end[i]; j++)
                coverInterval[j]++;
        }

        for(int i = 0; i < totalLifeguards; i++)
        {
            for(int j = start[i]; j < end[i]; j++)
            {
                coverInterval[j]--;
            }
            int maxCover = 0;
            for(int j = 0; j < 100000000; j++)
            {
                if(coverInterval[j]>0)
                    maxCover ++;
            }
            maxTime = Math.max(maxTime, maxCover);
            for(int j = start[i]; j < end[i]; j++)
            {
                coverInterval[j]++;
            }
        }

        return maxTime;
    }
}
