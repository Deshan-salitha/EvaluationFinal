package com.ccms.hris.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class Notification {
    private static final Logger log = LoggerFactory.getLogger(Notification.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


//    @Scheduled(cron="0 */1 * * * ?", zone = "Europe/London")
//    public void triggerTrainings() throws IOException {
//        SimpMessagingTemplate.convertAndSend("/training/", "deliver");
//    }

}