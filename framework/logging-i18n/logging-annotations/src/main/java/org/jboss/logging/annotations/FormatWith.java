/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2015 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.logging.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Indicate that the given parameter should be wrapped with a formatting object of the given class.  The class
 * must have a one-argument constructor which unambiguously accepts a value of this parameter's type.  The resultant
 * object will be passed in as a parameter to the underlying format type; thus its {@link Object#toString() toString()}
 * method will be invoked (or, if the format style is {@link Message.Format#PRINTF PRINTF}, the object may implement
 * {@link java.util.Formattable Formattable} to get extra functionality).
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
@Target(PARAMETER)
@Retention(CLASS)
@Documented
public @interface FormatWith {

    /**
     * The class of the formatting object to use.
     *
     * @return the class
     */
    Class<?> value();

}
