package com.fast.learners.platform.iam.domain.model.commands;
import com.fast.learners.platform.iam.domain.model.entities.Membership;

public record SignUpCommand(String username, String password, Membership membership) {
}
