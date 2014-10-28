// JODConverter - Java OpenDocument Converter
// Copyright 2004-2012 Mirko Nasato and contributors
//
// JODConverter is Open Source software, you can redistribute it and/or
// modify it under either (at your option) of the following licenses
//
// 1. The GNU Lesser General Public License v3 (or later)
//    -> http://www.gnu.org/licenses/lgpl-3.0.txt
// 2. The Apache License, Version 2.0
//    -> http://www.apache.org/licenses/LICENSE-2.0.txt
//
//
package org.artofsolving.jodconverter.office;


abstract class Retryable <T extends Throwable> {
  /**
   * @throws TemporaryException
   *           for an error condition that can be temporary - i.e. retrying later could be successful
   * @throws RetryTimeoutException
   *           if this Retryable fails to complete its task in the given time
   * @throws T extends Throwable
   *           for all other error conditions
   */
  protected abstract void attempt() throws TemporaryException, RetryTimeoutException, T;

  public void execute(long interval, long timeout) throws RetryTimeoutException, T {
    execute(0L, interval, timeout);
  }

  public void execute(long delay, long interval, long timeout) throws RetryTimeoutException, T {
    long start = System.currentTimeMillis();
    if (delay > 0L) {
      sleep(delay);
    }
    while (true) {
      try {
        attempt();
        return;
      } catch (TemporaryException temporaryException) {
        if (System.currentTimeMillis() - start < timeout) {
          sleep(interval);
          // continue
        } else {
          throw new RetryTimeoutException(temporaryException.getCause());
        }
      }
    }
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException interruptedException) {
      // continue
    }
  }
}
