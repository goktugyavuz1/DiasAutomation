package dias.mobile.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "dias.mobile.automation",
        monochrome = true
)
public class TestRunner {

        @AfterClass
        public static void generateReport() {
                try {
                        File reportRootDir = new File("report");
                        if (!reportRootDir.exists()) {
                                reportRootDir.mkdirs();
                                System.out.println("📁 'report' directory created.");
                        }

                        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                        File reportOutputDir = new File(reportRootDir, "run_" + timestamp);
                        reportOutputDir.mkdirs();

                        List<String> jsonFiles = Collections.singletonList("target/cucumber.json");

                        Configuration config = new Configuration(reportOutputDir, "DiasMobileAutomation");
                        config.setBuildNumber(timestamp);
                        config.addClassifications("Platform", "iOS");
                        config.addClassifications("Environment", "QA");

                        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
                        reportBuilder.generateReports();

                } catch (Exception e) {
                        System.err.println("❌ Report generation failed: " + e.getMessage());
                }
        }
}
