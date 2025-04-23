import java.io.*;
import java.time.LocalDateTime;

public class Post {
    public static void main(String[] args) throws IOException {
        String email = args[0];
        String title = args[1];
        StringBuilder message = new StringBuilder();
        for (int i = 2; i < args.length; i++) message.append(args[i]).append(" ");
        String date = LocalDateTime.now().toString();

        String userDir = "users/" + email;
        new File(userDir).mkdirs(); // Create user directory if it doesn't exist

        File postsFile = new File(userDir + "/posts.dat");
        File indexFile = new File(userDir + "/posts.idx");

        String postEntry = email + "|" + date + "|" + title + "|" + message.toString().trim() + "\n";

        try (RandomAccessFile raf = new RandomAccessFile(postsFile, "rw");
             BufferedWriter idxWriter = new BufferedWriter(new FileWriter(indexFile, true))) {

            long position = raf.length();
            raf.seek(position);
            raf.writeBytes(postEntry);

            idxWriter.write(email + "|" + position);
            idxWriter.newLine();
        }

        System.out.println("Post saved to " + email + "'s mailbox.");
    }
}
