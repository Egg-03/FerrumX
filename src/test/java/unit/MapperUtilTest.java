package unit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.eggy03.ferrumx.windows.entity.processor.Processor;
import io.github.eggy03.ferrumx.windows.mapping.MapperUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapperUtilTest {

    @Test
    void testMapToObject_success() {

        JsonObject processorObject = new JsonObject();
        processorObject.addProperty("DeviceID", "CPU0");
        processorObject.addProperty("Name", "Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz");

        String jsonProcessor = new Gson().toJson(processorObject);

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

        JsonArray processorArrayObject = new JsonArray();

        JsonObject cpu0 = new JsonObject();
        cpu0.addProperty("DeviceID", "CPU0");
        cpu0.addProperty("Name", "Intel(R) Core(TM) i5-14700H CPU @ 2.30GHz");

        JsonObject cpu1 = new JsonObject();
        cpu1.addProperty("DeviceID", "CPU1");
        cpu1.addProperty("Name", "Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz");

        processorArrayObject.add(cpu0);
        processorArrayObject.add(cpu1);

        String jsonArrayProcessor = new Gson().toJson(processorArrayObject);

        List<Processor> processors = MapperUtil.mapToList(jsonArrayProcessor, Processor.class);
        assertEquals(2, processors.size());
        assertEquals("CPU0", processors.get(0).getDeviceId());
        assertEquals("Intel(R) Core(TM) i5-14700H CPU @ 2.30GHz", processors.get(0).getName());
        assertEquals("CPU1", processors.get(1).getDeviceId());
        assertEquals("Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz", processors.get(1).getName());

    }

    @Test
    void testMapToList_whenSingleObject_success() {

        JsonObject processorObject = new JsonObject();
        processorObject.addProperty("DeviceID", "CPU0");
        processorObject.addProperty("Name", "Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz");

        String jsonProcessor = new Gson().toJson(processorObject);

        List<Processor> processors = MapperUtil.mapToList(jsonProcessor, Processor.class);
        assertEquals(1, processors.size());
        assertEquals("CPU0", processors.get(0).getDeviceId());
        assertEquals("Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz", processors.get(0).getName());
    }

    @Test
    void testMapToList_invalidJson_empty() {

        String json = "invalid json";
        assertThrows(JsonSyntaxException.class, ()-> MapperUtil.mapToList(json, Processor.class));

    }
}
