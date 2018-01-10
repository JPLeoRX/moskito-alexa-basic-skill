package moskito.speech.notifications;

import moskito.services.AppsURL;
import moskito.services.rest.ThresholdsRest;
import moskito.services.rest.basic_entities.Threshold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThresholdNotification {
    private static final Logger LOGGER = LogManager.getLogger();

    private List<Threshold> thresholdsOld;
    private List<Threshold> thresholds;

    private boolean alert;

    private ScheduledExecutorService serviceAlert;
    private ScheduledExecutorService serviceThresholds;

    public void startThreads() {
        if (serviceAlert == null) {
            serviceAlert = Executors.newSingleThreadScheduledExecutor();
            serviceAlert.scheduleAtFixedRate(getMonitorAlertRunnable(), 0, 3, TimeUnit.SECONDS);
        }

        if (serviceThresholds == null) {
            serviceThresholds = Executors.newSingleThreadScheduledExecutor();
            serviceAlert.scheduleAtFixedRate(getMonitorThresholdsRunnable(), 0, 3, TimeUnit.SECONDS);
        }
    }

    public Runnable getMonitorAlertRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                if (alert) {
                    LOGGER.error("NOTIFICATION SHOWN!");
                    alert = false;
                }

                LOGGER.info("Alert thread is running");
            }
        };
    }

    public Runnable getMonitorThresholdsRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                if (!AppsURL.current.equalsIgnoreCase("")) {
                    // Read new thresholds
                    thresholds = new ThresholdsRest(AppsURL.current).getList();

                    // For each new threshold
                    for (Threshold thresholdNew : thresholds) {
                        // If we have old thresholds
                        if (thresholdsOld != null) {
                            // Find corresponding old one
                            Threshold thresholdOld = thresholdsOld.get(thresholdsOld.indexOf(thresholdNew));

                            // If they dont have the same status
                            if (thresholdOld.getStatus() != thresholdNew.getStatus())
                                alert = true;
                        }
                    }

                    // Save the new ones
                    thresholdsOld = thresholds;

                    LOGGER.info("Thresholds thread is running");
                }
            }
        };
    }
}
