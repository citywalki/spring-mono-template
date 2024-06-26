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

package com.mycim.logging.processor.model;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * A delegating {@link TypeElement} interface. All methods are invoked on the {@linkplain #getDelegate() delegate
 * element}
 * by default.
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public interface DelegatingTypeElement extends TypeElement, DelegatingElement {

    @Override
    TypeElement getDelegate();


    @Override
    default TypeMirror asType() {
        return getDelegate().asType();
    }

    @Override
    default List<? extends Element> getEnclosedElements() {
        return getDelegate().getEnclosedElements();
    }

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    default NestingKind getNestingKind() {
        return getDelegate().getNestingKind();
    }

    @Override
    default Name getQualifiedName() {
        return getDelegate().getQualifiedName();
    }

    @Override
    default Name getSimpleName() {
        return getDelegate().getSimpleName();
    }

    @Override
    default TypeMirror getSuperclass() {
        return getDelegate().getSuperclass();
    }

    @Override
    default List<? extends TypeMirror> getInterfaces() {
        return getDelegate().getInterfaces();
    }

    @Override
    default List<? extends TypeParameterElement> getTypeParameters() {
        return getDelegate().getTypeParameters();
    }

    @Override
    default Element getEnclosingElement() {
        return getDelegate().getEnclosingElement();
    }

}
