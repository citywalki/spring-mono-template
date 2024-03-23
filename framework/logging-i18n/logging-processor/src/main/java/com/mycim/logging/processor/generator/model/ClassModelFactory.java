package com.mycim.logging.processor.generator.model;

import com.mycim.logging.processor.model.MessageInterface;
import com.mycim.logging.processor.model.MessageMethod;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.MessageLogger;

import javax.annotation.processing.ProcessingEnvironment;
import java.util.Map;

import static com.mycim.logging.processor.generator.model.ClassModelHelper.implementationClassName;
import static com.mycim.logging.processor.util.TranslationHelper.getEnclosingTranslationClassName;

/**
 * Creates a class model for the message interface.
 */
public class ClassModelFactory {

    /**
     * Creates an implementation code model from the message interface.
     *
     * @param processingEnv    the processing environment
     * @param messageInterface the message interface to implement
     * @param useLogging31     whether or not jboss-logging 3.1 or higher is used
     * @return the class model used to implement the interface.
     * @throws IllegalArgumentException if interface is not annotated with {@link MessageBundle @MessageBundle} or
     * {@link MessageLogger @MessageLogger}
     */
    public static ClassModel implementation(final ProcessingEnvironment processingEnv,
                                            final MessageInterface messageInterface,
                                            final boolean useLogging31) throws IllegalArgumentException {
        if (messageInterface.isAnnotatedWith(MessageBundle.class)) {
            return new MessageBundleImplementor(processingEnv, messageInterface);
        }
        throw new IllegalArgumentException(
                String.format("Message interface %s is not a valid message logger or message bundle.",
                              messageInterface));
    }

    public static InterfaceModel translationI18n(final ProcessingEnvironment processingEnv,
                                                 final MessageInterface messageInterface,
                                                 final Map<String, Map<MessageMethod, String>> translations) throws IllegalArgumentException {
        final String generatedClassName = implementationClassName(messageInterface, "i18n");
        final String superClassName = getEnclosingTranslationClassName(generatedClassName);

        if (messageInterface.isAnnotatedWith(MessageBundle.class)) {
            return new XMessageBundleTranslator(processingEnv, messageInterface, generatedClassName, translations);
        }
        throw new IllegalArgumentException(
                String.format("Message interface %s is not a valid message logger or message bundle.",
                              messageInterface));
    }

}
