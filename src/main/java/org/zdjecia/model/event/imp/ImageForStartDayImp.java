package org.zdjecia.model.event.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zdjecia.model.event.ImageForStartDay;

@Component
public class ImageForStartDayImp implements ImageForStartDay {
    private static final Logger LOG = LoggerFactory.getLogger(ImageForStartDayImp.class);

    @Scheduled(cron = "0 0 7 * * ?",zone = "GMT+2:00")
    public void sendEmail(){
        LOG.info("WYSŁANIE EMAILA!!!!");
    }
}
