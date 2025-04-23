import java.io.*;
import java.util.*;

public class Timeline {
    public static void main(String[] args) throws IOException {
        String email = args[0];
        Set<String> following = new HashSet<>();
        Map<String, List<Long>> userPostOffsets = new HashMap<>();

        String userDir = "users/" + email;
        File followFile = new File(userDir + "/follows.dat");

        // Step 1: Memuat siapa yang diikuti pengguna
        if (followFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(followFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    following.add(parts[2]);
                }
            }
        }

        // Step 2: Memuat indeks setiap pengguna yang diikuti
        for (String user : following) {
            File indexFile = new File("users/" + user + "/posts.idx");
            if (indexFile.exists()) {
                try (BufferedReader indexReader = new BufferedReader(new FileReader(indexFile))) {
                    String line;
                    while ((line = indexReader.readLine()) != null) {
                        String[] parts = line.split("\\|");
                        long offset = Long.parseLong(parts[1]);
                        userPostOffsets.putIfAbsent(user, new ArrayList<>());
                        userPostOffsets.get(user).add(offset);
                    }
                }
            }
        }

        // Step 3: Baca postingan dari setiap pengguna yang diikuti
        for (String user : userPostOffsets.keySet()) {
            File postsFile = new File("users/" + user + "/posts.dat");
            if (postsFile.exists()) {
                try (RandomAccessFile raf = new RandomAccessFile(postsFile, "r")) {
                    for (long offset : userPostOffsets.get(user)) {
                        raf.seek(offset);
                        String postLine = raf.readLine();
                        if (postLine != null) {
                            String[] parts = postLine.split("\\|");
                            System.out.println("User: " + parts[0]);
                            System.out.println("Title: " + parts[2]);
                            System.out.println("Message: " + parts[3]);
                            System.out.println("Date: " + parts[1]);
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
