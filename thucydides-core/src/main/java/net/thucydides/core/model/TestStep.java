package net.thucydides.core.model;

import static net.thucydides.core.model.TestResult.FAILURE;
import static net.thucydides.core.model.TestResult.IGNORED;
import static net.thucydides.core.model.TestResult.PENDING;
import static net.thucydides.core.model.TestResult.SKIPPED;
import static net.thucydides.core.model.TestResult.SUCCESS;

import java.io.File;

import com.google.common.base.Preconditions;

/**
 * An acceptence test run is made up of test steps.
 * Each step should represent an action by the user, and (generally) an expected outcome.
 * A test step is described by a narrative-style phrase (e.g. "the user clicks 
 * on the 'Search' button', "the user fills in the registration form', etc.).
 * A screenshot is stored for each step.
 * 
 * @author johnsmart
 *
 */
public class TestStep {

    private final String description;    
    private File screenshot;
    private TestResult result;
    private String errorMessage;
    private Throwable cause;
    
    public TestStep(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Each test step can be associated with a screenshot.
     */
    public void setScreenshot(final File screenshot) {
        Preconditions.checkState(this.screenshot == null, 
                                 "Test steps are immutable - a screenshot can only be assigned once.");
        this.screenshot = screenshot;
    }
    
    /**
     * Each test step has a result, indicating the outcome of this step.
     */
    public void setResult(final TestResult result) {
        Preconditions.checkState(this.result == null, 
                                 "Test steps are immutable - the test result can only be assigned once.");
        this.result = result;
    }
    
    /**
     * Indicate that this step failed with a given error.
     */
    public void failedWith(final String message, final Throwable e) {
        this.errorMessage = message;
        this.cause = e;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public TestResult getResult() {
        return result;
    }
    
    public File getScreenshot() {
        return screenshot;
    }

    public Boolean isSuccessful() {
        return getResult() == SUCCESS;
    }

    public Boolean isFailure() {
        return  getResult() == FAILURE;
    }

    public Boolean isIgnored() {
        return  getResult() == IGNORED;
    }

    public Boolean isSkipped() {
        return  getResult() == SKIPPED;
    }

    public Boolean isPending() {
        return  getResult() == PENDING;
    }

    public Throwable getException() {
        return cause;
    }

}