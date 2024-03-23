/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.mycim.logging.processor.apt;

import com.mycim.logging.processor.generator.model.ClassModelFactory;
import com.mycim.logging.processor.generator.model.InterfaceModel;
import com.mycim.logging.processor.model.MessageInterface;
import com.mycim.logging.processor.model.MessageMethod;
import com.mycim.logging.processor.validation.FormatValidator;
import com.mycim.logging.processor.validation.FormatValidatorFactory;
import com.mycim.logging.processor.validation.StringFormatValidator;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.MessageLogger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static com.mycim.logging.processor.util.TranslationHelper.getTranslationClassNameSuffix;

/**
 * The translation class generator.
 * <p>
 * The aim of this generator is to generate
 * the classes corresponding to translation
 * files of a MessageLogger or MessageBundle.
 * </p>
 *
 * @author Kevin Pollet - SERLI - (kevin.pollet@serli.com)
 */
@SupportedOptions(
        {TranslationClassGenerator.TRANSLATION_FILES_PATH_OPTION, TranslationClassGenerator.SKIP_TRANSLATIONS})
final class TranslationClassGenerator extends AbstractGenerator {

    public static final String TRANSLATION_FILES_PATH_OPTION = "translationFilesPath";

    public static final String SKIP_TRANSLATIONS = "skipTranslations";

    /**
     * The properties file pattern. The property file must
     * match the given pattern <em>org.pkgname.InterfaceName.i18n_locale.properties</em> where locale is :
     * <ul>
     * <li>xx - where xx is the language like (e.g. en)</li>
     * <li>xx_YY - where xx is the language and YY is the country like (e.g. en_US)</li>
     * <li>xx_YY_ZZ - where xx is the language, YY is the country and ZZ is the variant like (e.g. en_US_POSIX)</li>
     * </ul>
     */
    private static final String TRANSLATION_FILE_EXTENSION_PATTERN = ".i18n_[a-z]*(_[A-Z]*){0,2}\\.properties";

    private final String translationFilesPath;

    private final boolean skipTranslations;


    /**
     * Construct an instance of the Translation
     * Class Generator.
     *
     * @param processingEnv the processing environment
     */
    public TranslationClassGenerator(final ProcessingEnvironment processingEnv) {
        super(processingEnv);
        Map<String, String> options = processingEnv.getOptions();
        this.translationFilesPath = options.get(TRANSLATION_FILES_PATH_OPTION);
        final String value = options.get(SKIP_TRANSLATIONS);
        this.skipTranslations = (options.containsKey(SKIP_TRANSLATIONS) &&
                (value == null ? true : Boolean.valueOf(value)));
    }

    private static FormatValidator getValidatorFor(final MessageMethod messageMethod, final String translationMessage) {
        FormatValidator result = FormatValidatorFactory.create(messageMethod.message().format(), translationMessage);
        if (result.isValid()) {
            if (messageMethod.message().format() == Message.Format.PRINTF) {
                result = StringFormatValidator.withTranslation(messageMethod.message().value(), translationMessage);
            }
        }
        return result;
    }

    @Override
    public void processTypeElement(final TypeElement annotation, final TypeElement element,
                                   final MessageInterface messageInterface) {
        if (skipTranslations) {
            logger().debug(element, "Skipping processing of translation implementation");
            return;
        }
        try {
            final List<File> files = findTranslationFiles(messageInterface);
            final Map<File, Map<MessageMethod, String>> validTranslations = allInterfaceTranslations(messageInterface,
                                                                                                     files);
            generateSourceFileFor(messageInterface, validTranslations);
        } catch (IOException e) {
            logger().error(e, "Cannot read %s package files", messageInterface.packageName());
        }
    }

    private Map<File, Map<MessageMethod, String>> allInterfaceTranslations(final MessageInterface messageInterface,
                                                                           final List<File> files) throws IOException {
        final Map<File, Map<MessageMethod, String>> validTranslations = new LinkedHashMap<>();
        for (MessageInterface superInterface : messageInterface.extendedInterfaces()) {
            validTranslations.putAll(allInterfaceTranslations(superInterface, findTranslationFiles(superInterface)));
        }
        if (files != null) {
            for (File file : files) {
                validTranslations.put(file, validateTranslationMessages(messageInterface, file));
            }
        }
        return validTranslations;
    }

    private List<File> findTranslationFiles(final MessageInterface messageInterface) throws IOException {
        final String packageName = messageInterface.packageName();
        final String interfaceName = messageInterface.simpleName();

        final String classTranslationFilesPath;

        //User defined
        if (translationFilesPath != null) {
            classTranslationFilesPath = translationFilesPath;
            // classTranslationFilesPath = translationFilesPath + packageName.replace('.', File.separatorChar);

            //By default use the class output folder
        } else {
            FileObject fObj = processingEnv
                    .getFiler()
                    .getResource(StandardLocation.CLASS_OUTPUT, packageName, interfaceName);
            classTranslationFilesPath = fObj.toUri().getPath().replace(interfaceName, "");
        }
        final List<File> result;
        File[] files = new File(classTranslationFilesPath).listFiles(new TranslationFileFilter(interfaceName));
        if (files == null) {
            result = Collections.emptyList();
        } else {
            result = Arrays.asList(files);
            Collections.sort(result, new Comparator<File>() {
                public int compare(final File o1, final File o2) {
                    int result = o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
                    result = (result != 0 ? result : Integer.signum(o1.getName().length() - o2.getName().length()));
                    return result;
                }
            });
        }
        return result;

    }

    /**
     * Returns only the valid translations message corresponding
     * to the declared {@link com.mycim.logging.processor.model.MessageMethod} methods in the
     * {@link MessageBundle} or {@link MessageLogger}
     * interface.
     *
     * @param messageInterface the message interface.
     * @param file             the translation file
     * @return the valid translations messages
     */
    private Map<MessageMethod, String> validateTranslationMessages(final MessageInterface messageInterface,
                                                                   final File file) {
        Map<MessageMethod, String> validTranslations = new LinkedHashMap<>();

        try {

            //Load translations
            Properties translations = new Properties();
            translations.load(new InputStreamReader(new FileInputStream(file), "utf-8"));
            final Set<MessageMethod> messageMethods = new LinkedHashSet<>();
            messageMethods.addAll(messageInterface.methods());
            for (MessageInterface msgIntf : messageInterface.extendedInterfaces()) {
                if (msgIntf.isAnnotatedWith(MessageBundle.class) || msgIntf.isAnnotatedWith(MessageLogger.class)) {
                    messageMethods.addAll(msgIntf.methods());
                }
            }
            for (MessageMethod messageMethod : messageMethods) {
                final String key = messageMethod.translationKey();
                if (translations.containsKey(key)) {
                    final String translationMessage = translations.getProperty(key);
                    if (!translationMessage.trim().isEmpty()) {
                        final FormatValidator validator = getValidatorFor(messageMethod, translationMessage);
                        if (validator.isValid()) {
                            if (validator.argumentCount() == messageMethod.formatParameterCount()) {
                                validTranslations.put(messageMethod, translationMessage);
                            } else {
                                logger().warn(messageMethod,
                                              "The parameter count for the format (%d) and the number of format " +
                                                      "parameters (%d) do not match.", validator.argumentCount(),
                                              messageMethod.formatParameterCount());
                            }
                        } else {
                            logger().warn(messageMethod, "%s Resource Bundle: %s", validator.summaryMessage(),
                                          file.getAbsolutePath());
                        }
                    } else {
                        logger().warn(messageMethod,
                                      "The translation message with key %s is ignored because value is empty or " +
                                              "contains only whitespace", key);
                    }

                } else {
                    logger().warn(messageMethod,
                                  "The translation message with key %s have no corresponding messageMethod.", key);
                }
            }

        } catch (IOException e) {
            logger().error(e, "Cannot read the %s translation file", file.getName());
        }

        return validTranslations;
    }

    private void generateSourceFileFor(final MessageInterface messageInterface,
                                       final Map<File, Map<MessageMethod, String>> translations) {

        final Map<String, Map<MessageMethod, String>> finalTranslations = new LinkedHashMap<>();
        for (File file : translations.keySet()) {
            finalTranslations.put(getTranslationClassNameSuffix(file.getName()), translations.get(file));
        }

        //Create source file
        final InterfaceModel classModel = ClassModelFactory.translationI18n(processingEnv, messageInterface,
                                                                            finalTranslations);

        try {
            classModel.generateAndWrite();
        } catch (IllegalStateException | IOException e) {
            logger().error(e, "Cannot generate %s source file", classModel.qualifiedClassName());
        }
    }

    /**
     * Translation file Filter.
     */
    private class TranslationFileFilter implements FilenameFilter {

        private final String className;

        /**
         * The property file filter.
         *
         * @param className the class that have i18n property file
         */
        public TranslationFileFilter(final String className) {
            this.className = className;
        }

        @Override
        public boolean accept(final File dir, final String name) {

            boolean isGenerated = name.endsWith(".i18n_locale_COUNTRY_VARIANT.properties");
            boolean isTranslationFile = name.matches(Pattern.quote(className) + TRANSLATION_FILE_EXTENSION_PATTERN);

            return !isGenerated && isTranslationFile;
        }

    }

}
