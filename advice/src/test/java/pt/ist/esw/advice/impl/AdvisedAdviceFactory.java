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
package pt.ist.esw.advice.impl;

import java.util.concurrent.Callable;

import pt.ist.esw.advice.Advice;
import pt.ist.esw.advice.AdviceFactory;

public final class AdvisedAdviceFactory extends AdviceFactory<Advised> {

    public static class MyOtherAdvice implements Advice {

        public <V> V perform(Callable<V> method) throws Exception {
            method.call();
            return method.call();
        }
    }

    private AdvisedAdviceFactory() {
    }

    private final static AdvisedAdviceFactory instance = new AdvisedAdviceFactory();

    public static AdviceFactory<Advised> getInstance() {
        return instance;
    }

    @Override
    public Advice newAdvice(Advised annotation) {
        return new MyOtherAdvice();
    }

}
