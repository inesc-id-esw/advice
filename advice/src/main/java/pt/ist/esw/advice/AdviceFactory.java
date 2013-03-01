/*
 * Advice Library
 * Copyright (C) 2012 INESC-ID Software Engineering Group
 * http://www.esw.inesc-id.pt
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Author's contact:
 * INESC-ID Software Engineering Group
 * Rua Alves Redol 9
 * 1000 - 029 Lisboa
 * Portugal
 */
package pt.ist.esw.advice;

import java.lang.annotation.Annotation;

public abstract class AdviceFactory<T extends Annotation> {
    /**
     * Default AdviceFactory used, when none is specified in the annotation.
     * Clients must either provide this class or define the property <code>Class<? extends AdviceFactory> adviceFactory() </code>
     * in their annotation.
     **/
    public static final String DEFAULT_ADVICE_FACTORY = "pt.ist.esw.advice.impl.ClientAdviceFactory";

    /** AdviceFactories must override this method **/
    public static <T extends Annotation> AdviceFactory<T> getInstance() {
        throw new UnsupportedOperationException("Clients must provide an AdviceFactory with a 'getInstance()' method.");
    }

    /** AdviceFactories must override this method **/
    public abstract Advice newAdvice(T annotation);

}
