package org.acme.scheduler;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeCountResourceIT extends CountResourceTest {

    // Execute the same tests but in native mode.
}