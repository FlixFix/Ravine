package com.flixfix.bikechecker.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CanyonBikeCheckerImpl implements CanyonBikeChecker {

    @Value("${spring.bikes.details.urls}")
    private List<String> urls;

    @Value("${spring.bikes.details.sizes}")
    private List<String> sizes;

    @Value("${spring.bikes.details.soon-msg}")
    private String soonMsg;

    private final EmailService emailService;

    @Override
    @Scheduled(cron = "${spring.crawler-job.cron}")
    public void checkBikeDetailUrlsFromConfig() {
        log.info("running crawler");
        for (var url : urls) {
            try {
                var doc = Jsoup.connect(url).get();
                var sizeVariations = doc.select("div.productConfiguration__selectVariant.js-nonSelectableVariation");
                for (var variant : sizeVariations) {
                    var size = variant.attr("data-product-size");
                    if (sizes.stream().anyMatch(s -> s.equals(size))) {
                        checkSizeAvailability(variant);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void checkSizeAvailability(Element element) {
            var productSize = element.attr("data-product-size");
            var availability = element.select("div.productConfiguration__availability");
            var msg = availability.select("div.productConfiguration__availabilityMessage").text();
            if (!msg.equals(soonMsg)) {
                emailService.sendEmail(productSize);
            }
            log.info(String.format("%s - %s", productSize, msg));
    }
}
