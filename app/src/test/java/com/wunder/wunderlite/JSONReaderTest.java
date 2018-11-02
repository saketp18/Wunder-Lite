package com.wunder.wunderlite;

import com.wunder.utils.JSONReader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONReaderTest {

    @Test
    public void readFile() {
        String filepath = null;
        assertEquals(JSONReader.Companion.readJson(null), filepath);
    }
}
