/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.factory;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.TestResourceFinder;
import com.github.wiselenium.factory.exception.PageInstantiationException;
import com.github.wiselenium.page.DummyPageWithNoArgConstructor;
import com.github.wiselenium.page.DummyPageWithWebDriverConstructor;
import com.github.wiselenium.page.DummyPageWithoutProperConstructor;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends TestBase {
	
	private static final String DUMMY_PAGE = TestResourceFinder.getAbsolutePath("button.html");
	
	@BeforeMethod
	public void goToPage() {
		this.driver.get(DUMMY_PAGE);
	}
	
	@Test
	public void shouldCreatePageWithWebDriverConstructorAndInitElements() {
		DummyPageWithWebDriverConstructor page = WisePageFactory.initElements(
				this.driver,
				DummyPageWithWebDriverConstructor.class);
		assertNotNull(page.getDummyComponent());
	}
	
	@Test
	public void shouldCreatePageWithNoArgConstructorAndInitElements() {
		DummyPageWithNoArgConstructor page = WisePageFactory.initElements(this.driver, DummyPageWithNoArgConstructor.class);
		assertNotNull(page.getDummyComponent());
	}
	
	@Test
	public void shouldInitElementsOfInstance() {
		DummyPageWithWebDriverConstructor page = new DummyPageWithWebDriverConstructor(this.driver);
		WisePageFactory.initElements(this.driver, page);
		assertNotNull(page.getDummyComponent());
	}
	
	@Test(expectedExceptions = PageInstantiationException.class)
	public void shouldThrowExceptionWhileInstantiatingPageWithoutProperConstructor() {
		WisePageFactory.initElements(this.driver, DummyPageWithoutProperConstructor.class);
	}
	
}
