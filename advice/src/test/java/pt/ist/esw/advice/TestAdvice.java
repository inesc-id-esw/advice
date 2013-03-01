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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pt.ist.esw.advice.impl.Advised;

/**
 *
 */
public class TestAdvice {

    public int i;

    @Before
    public void resetCounter() {
        i = 0;
    }

    @Test
    public void testAdviceRunsOnce() {
        assertEquals(0, i);
        // run inc() advised, which should run it twice
        inc();
        assertEquals(2, i);
    }

    @Test
    public void testAdviceRunsTwice() {
        assertEquals(0, i);
        // run inc() advised, which should run it twice
        inc();
        // run inc() advised, which should run it twice
        inc();
        assertEquals(4, i);
    }

    @Advised
    @Deprecated
    private void inc() {
        i++;
    }

}
