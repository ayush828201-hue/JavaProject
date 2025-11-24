package com.vityarthi.project.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ReportGenerator implements Runnable {

    private final List<String> reportData;
    private final double classAverage;
    private static final String FILE_NAME = "class_summary_report.txt";

    public ReportGenerator(List<String> reportData, double classAverage) {
        this.reportData = reportData;
        this.classAverage = classAverage;
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("\n[Thread] Report generation started...");
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                writer.println("=============================================");
                writer.println("      CLASS SUMMARY REPORT");
                writer.println("=============================================");
                writer.println("Generated On: " + dateFormat.format(new Date()));
                writer.println("---------------------------------------------");
                writer.println();

                if (reportData.isEmpty()) {
                    writer.println("No grade data available to generate a report.");
                } else {
                    for (String line : reportData) {
                        writer.println(line);
                    }
                    writer.println();
                    writer.println("---------------------------------------------");
                    writer.printf("Overall Class Average Score: %.2f%n", classAverage);
                    writer.println("=============================================");
                }

                Thread.sleep(1000);

                System.out.println("[Thread] Report successfully generated and saved to " + FILE_NAME);

            } catch (IOException e) {
                System.err.println("[Thread] Error writing report to file: " + e.getMessage());
            } catch (InterruptedException e) {
                System.err.println("[Thread] Report generation was interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}