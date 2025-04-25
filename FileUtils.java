import java.io.*;

public class FileUtils {
    private static final Object lock = new Object();
    private static boolean writeCompleted = false;

    public static void WriteToFile(String fileName, String content) {
        Thread writerThread = new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(content);
                System.out.println("Data written to file: " + fileName);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

            synchronized (lock) {
                writeCompleted = true;
                lock.notifyAll();
            }
        });
        writerThread.start();
    }

    public static String ReadFromFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return content.toString().trim();
    }

    public static void WaitForWriteCompletion() {
        synchronized (lock) {
            while (!writeCompleted) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.err.println("Waiting interrupted: " + e.getMessage());
                }
            }
        }
    }
}
