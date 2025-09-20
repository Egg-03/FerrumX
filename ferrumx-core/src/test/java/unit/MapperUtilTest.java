package unit;

import com.google.gson.JsonSyntaxException;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.util.MapperUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapperUtilTest {

    @Test
    void testMapToObject_success() {

        String jsonProcessor = """
                {
                  "DeviceID": "CPU0",
                  "Name": "Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz"
                }
                """;
        Optional<Processor> processor = MapperUtil.mapToObject(jsonProcessor, Processor.class);
        assertTrue(processor.isPresent());
        assertEquals("CPU0", processor.get().getDeviceId());
        assertEquals("Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz", processor.get().getName());
    }

    @Test
    void testMapToObject_invalidJson_empty() {

        String json = "invalid json";
        assertThrows(JsonSyntaxException.class, ()-> MapperUtil.mapToObject(json, Processor.class));

    }

    @Test
    void testMapToList_success() {

        String jsonArrayProcessor = """
                [
                  {
                    "DeviceID": "CPU0",
                    "Name": "Intel(R) Core(TM) i5-14700H CPU @ 2.30GHz"
                  },
                  {
                    "DeviceID": "CPU1",
                    "Name": "Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz"
                  }
                ]
                """;
        List<Processor> processors = MapperUtil.mapToList(jsonArrayProcessor, Processor.class);
        assertEquals(2, processors.size());
        assertEquals("CPU0", processors.get(0).getDeviceId());
        assertEquals("Intel(R) Core(TM) i5-14700H CPU @ 2.30GHz", processors.get(0).getName());
        assertEquals("CPU1", processors.get(1).getDeviceId());
        assertEquals("Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz", processors.get(1).getName());

    }

    @Test
    void testMapToList_whenSingleObject_success() {

        String jsonProcessor = """
                {
                  "DeviceID": "CPU0",
                  "Name": "Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz"
                }
                """;
        List<Processor> processors = MapperUtil.mapToList(jsonProcessor, Processor.class);
        assertEquals(1, processors.size());
        assertEquals("CPU0", processors.getFirst().getDeviceId());
        assertEquals("Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz", processors.getFirst().getName());
    }

    @Test
    void testMapToList_invalidJson_empty() {

        String json = "invalid json";
        assertThrows(JsonSyntaxException.class, ()-> MapperUtil.mapToList(json, Processor.class));

    }
}
