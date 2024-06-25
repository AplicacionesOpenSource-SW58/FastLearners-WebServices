package com.fast.learners.platform.iam.application.internal.eventhandlers;

import com.fast.learners.platform.iam.domain.model.commands.SeedMembershipsCommand;
import com.fast.learners.platform.iam.domain.services.MembershipCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * ApplicationReadyEventHandler class
 * This class is used to handle the ApplicationReadyEvent
 */
@Service
public class ApplicationReadyEventHandler {
    private final MembershipCommandService membershipCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(MembershipCommandService membershipCommandService) {
        this.membershipCommandService = membershipCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the memberships
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if memberships seeding is needed for {} at {}", applicationName, currentTimestamp());
        var seedMembershipsCommand = new SeedMembershipsCommand();
        membershipCommandService.handle(seedMembershipsCommand);
        LOGGER.info("Memberships seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}