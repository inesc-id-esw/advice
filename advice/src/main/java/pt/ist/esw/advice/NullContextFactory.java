/*
 * AtomicAnnotation
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Implements the default strategy of delegating Context creation to the class named in
 * Atomic.DEFAULT_CONTEXT_FACTORY.
 **/
public final class NullContextFactory extends AdviceFactory {
    private NullContextFactory() {
    }

    public static Advice newContext(Atomic atomic) {
        try {
            Class<?> defaultFactory = Class.forName(Atomic.DEFAULT_CONTEXT_FACTORY);
            Method newContext = defaultFactory.getMethod("newContext", Atomic.class);
            return (Advice) newContext.invoke(null, atomic);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Default AdviceFactory " + Atomic.DEFAULT_CONTEXT_FACTORY
                    + " was not provided, nor a custom one was specified", e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
