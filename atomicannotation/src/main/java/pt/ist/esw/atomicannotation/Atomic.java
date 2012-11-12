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
package pt.ist.esw.atomicannotation;

import java.lang.annotation.*;

// Unfortunately, we cannot have "null" as a default value for the contextFactory parameter,
// and instead we employ this class as a marker to substitute for it.
final class NullContextFactory extends ContextFactory {
    private NullContextFactory() { }
};

@Target(ElementType.METHOD)
public @interface Atomic {
    boolean readOnly() default false;
    boolean canFail()  default true;
    boolean speculativeReadOnly() default true;
    Class<? extends ContextFactory> contextFactory() default NullContextFactory.class;
}
