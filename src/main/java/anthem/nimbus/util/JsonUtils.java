package anthem.nimbus.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

/**
 * FastJson
 *
 * @author Nageswara rao
 */
@UtilityClass
public final class JsonUtils {


    private static final SerializerFeature[] SERIALIZER_FEATURES = {
            SerializerFeature.NotWriteRootClassName,

            SerializerFeature.WriteNonStringKeyAsString,
           
            SerializerFeature.DisableCircularReferenceDetect
    };

   
    private static final Feature[] PARSE_FEATURES = {
            Feature.DisableCircularReferenceDetect
    };

    public static String toJson(Object object) {
        return JSON.toJSONString(object, SERIALIZER_FEATURES);
    }

    public static String toDefaultJson(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz, PARSE_FEATURES);
    }

   
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    
    public static List<Map<String, Object>> parseMap(String json) {
        return JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {}, PARSE_FEATURES);
    }

}