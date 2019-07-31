package utils;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HashUtilTest {

    private static final String PASSWORD = "12345";
    private static final String SALT = "qwerty";

    @Test
    public void getHash() {
        String actualHash = HashUtil.getHash(PASSWORD, SALT);
        String expectedHash = "ba467682f4b8c0a1f4f74137cf39598569e13692eea3146b9107ed61888bb499";
        assertEquals(actualHash, expectedHash);
    }
}
