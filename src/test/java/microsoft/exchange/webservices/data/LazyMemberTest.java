/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
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

package microsoft.exchange.webservices.data;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LazyMemberTest {

  LazyMember impl;

  @Mock ILazyMember<Object> iLazyMember;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
    impl = new LazyMember(iLazyMember);

    doReturn(new Object()).when(iLazyMember).createInstance();

  }

  @Test public void testGetMember() throws Exception {
    impl.getMember();
    impl.getMember();

    //createInstance has been called only one time
    verify(iLazyMember, times(1)).createInstance();

  }

  @Test public void testGetMemberMultiThread() throws Exception {
    final int poolSize = 3;
    final CountDownLatch latch = new CountDownLatch(poolSize);
    final ExecutorService pool = Executors.newFixedThreadPool(poolSize);

    final Runnable runnableUsingSingleton = new Runnable() {
      @Override public void run() {
        try {
          //just to ensure all threads will try to get the signleton at the same time
          latch.await();
          impl.getMember();
        } catch (InterruptedException e) {
          fail(e.getMessage());
        }
      }
    };

    for(int i = 0; i < poolSize; ++i) {
      pool.submit(runnableUsingSingleton);
      latch.countDown();  //decrease countdown
    }

    pool.shutdown();
    pool.awaitTermination(3, TimeUnit.SECONDS);

    //createInstance has been called only one time
    verify(iLazyMember, times(1)).createInstance();

  }
}