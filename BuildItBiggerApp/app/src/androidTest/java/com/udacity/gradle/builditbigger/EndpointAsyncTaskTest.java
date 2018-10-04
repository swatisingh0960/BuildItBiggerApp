package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class EndpointAsyncTaskTest {
@Test
    public void testFun() throws InterruptedException {
    assertTrue(true);
    final CountDownLatch myLathch = new CountDownLatch(1);
    final EndpointAsyncTask myTask = new EndpointAsyncTask(InstrumentationRegistry.getContext()){
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            assertNotNull(s);
            if (s !=null){
                assertTrue(true);
            }
            myLathch.countDown();
        }

    };
    myTask.execute();
    myLathch.await();

    }

}