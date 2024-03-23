package com.mycim.logging.processor.generator.model;

import com.mycim.logging.processor.model.MessageInterface;
import com.mycim.logging.processor.model.MessageMethod;
import org.jboss.jdeparser.JClassDef;
import org.jboss.jdeparser.JMethodDef;

import javax.annotation.processing.ProcessingEnvironment;
import java.util.*;

public class XMessageBundleTranslator extends InterfaceModel {

    /**
     * The translation map.
     */
    private final Map<String, Map<MessageMethod, String>> translations;


    /**
     * Construct a class model.
     *
     * @param processingEnv    the processing environment
     * @param messageInterface the message interface to implement.
     * @param className
     */
    XMessageBundleTranslator(final ProcessingEnvironment processingEnv, final MessageInterface messageInterface,
                             final String className, Map<String, Map<MessageMethod, String>> translations) {
        super(processingEnv, messageInterface, className, null);
        if (translations != null) {
            this.translations = translations;
        } else {
            this.translations = Collections.emptyMap();
        }
    }

    @Override
    public JClassDef generateModel() throws IllegalStateException {
        JClassDef classDef = super.generateModel();

        Map<MessageMethod, Set<String>> methodLanguages = new LinkedHashMap<>();
        for (String locale : translations.keySet()) {
            final Set<Map.Entry<MessageMethod, String>> entries = translations.get(locale).entrySet();
            for (Map.Entry<MessageMethod, String> entry : entries) {
                JMethodDef method = addMessageMethod(entry.getKey(), entry.getKey().messageMethodName() + locale,
                                                     entry.getValue());
                methodLanguages.compute(entry.getKey(), (messageMethod, strings) -> {
                    if (strings == null) {
                        strings = new HashSet<>();
                    }
                    strings.add(locale);
                    return strings;
                });
            }
        }

        for (MessageMethod messageMethod : messageInterface().methods()) {
            addI18nMessageMethod(messageMethod, methodLanguages.get(messageMethod));

        }

        return classDef;
    }

}
