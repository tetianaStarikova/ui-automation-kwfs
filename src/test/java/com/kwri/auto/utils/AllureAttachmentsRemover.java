package com.kwri.auto.utils;

import io.qameta.allure.AllureResultsWriteException;
import io.qameta.allure.listener.ContainerLifecycleListener;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.FixtureResult;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;
import io.qameta.allure.util.PropertiesUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * This class removes allure attachments from passed tests in order to reduce report size.
 */
public class AllureAttachmentsRemover implements TestLifecycleListener, ContainerLifecycleListener {

    private static final Properties PROPERTIES = PropertiesUtils.loadAllureProperties();
    private static final Path OUTPUT_DIRECTORY =
            Paths.get(PROPERTIES.getProperty("allure.results.directory", "allure-results"));
    private static final String PATTERN_FOR_REMOVE =
            PROPERTIES.getProperty("allure.report.remove.attachments", "");

    @Override
    public void beforeTestWrite(TestResult testResult) {
        if (testResult.getStatus() != null && testResult.getStatus().equals(Status.PASSED)) {
            removeMatchingAttachments(testResult.getAttachments());
            testResult.getSteps().stream()
                    .map(StepResult::getAttachments)
                    .forEach(this::removeMatchingAttachments);
            testResult.getSteps().stream()
                    .flatMap(this::getSubSteps)
                    .map(StepResult::getAttachments)
                    .forEach(this::removeMatchingAttachments);
        }
    }

    /**
     * Sub-step getter.
     *
     * @param step target step
     * @return stream of sub-steps
     */
    private Stream<StepResult> getSubSteps(StepResult step) {
        if (step.getSteps().isEmpty()) {
            return Stream.of(step);
        }
        return step.getSteps().stream().flatMap(this::getSubSteps);
    }

    /**
     * Duplicate attachment remover.
     *
     * @param attachments collection of attachments
     */
    private void removeMatchingAttachments(Collection<Attachment> attachments) {
        List<Attachment> attachmentsToRemove = attachments.stream()
                .filter(attachment -> attachment.getName().matches(PATTERN_FOR_REMOVE))
                .toList();
        attachmentsToRemove.forEach(this::deleteAttachmentFile);
        attachments.removeAll(attachmentsToRemove);
    }

    /**
     * Attachment remover.
     *
     * @param attachment to remove
     */
    private void deleteAttachmentFile(Attachment attachment) {
        Path filePath = OUTPUT_DIRECTORY.resolve(attachment.getSource());
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not remove Allure attachment", e);
        }
    }

    @Override
    public void beforeContainerWrite(TestResultContainer container) {
        ContainerLifecycleListener.super.beforeContainerWrite(container);
        for (FixtureResult fixtureResult : container.getAfters()) {
            removeMatchingAttachments(fixtureResult.getAttachments());
        }
    }
}
