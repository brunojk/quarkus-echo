package org.acme.e2e;

import io.quarkus.test.junit.NativeImageTest;
import org.acme.e2e.EchoResourceTest;

@NativeImageTest
public class NativeEchoResourceIT extends EchoResourceTest {

    // Execute the same tests but in native mode.
}