package pro.walkin.framework.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.dromara.hutool.core.math.NumberUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonKit {

    private static ObjectMapper defaultObjectMapper;

    public static ObjectMapper getDefaultObjectMapper() {
        if (defaultObjectMapper == null) {
            defaultObjectMapper = new ObjectMapper();
        }
        return defaultObjectMapper;
    }

    public static void setDefaultObjectMapper(ObjectMapper defaultObjectMapper) {
        JsonKit.defaultObjectMapper = defaultObjectMapper;
    }

    public static <T> T findValue(String jsonStr, String fieldName, Class<T> valueType) {
        return findValue(jsonStr, fieldName, valueType, getDefaultObjectMapper());
    }

    public static <T> T findValue(String jsonStr, String fieldName, Class<T> valueType, ObjectMapper om) {
        try {
            JsonNode fieldNode = om.readTree(jsonStr).findValue(fieldName);
            return om.convertValue(fieldNode, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取JSON中的某一个值
     *
     * <pre>
     * 这个方法返回的是String格式。
     * 建议使用这个方法来方便地取得目标值，而不是转化为临时的Map对象后通过map.get("key")取得。
     * </pre>
     *
     * @param json     目标JSON字符串
     * @param nodeKeys 抵达目标节点所有的节点key或数组下标
     * @return 目标值
     */
    public static String getValue(String json, String... nodeKeys) {
        return getValue(json, getDefaultObjectMapper(), nodeKeys);
    }

    /**
     * 读取JSON中的某一个具体值
     *
     * <pre>
     * 这个方法返回的是String格式。
     * 建议使用这个方法来方便地取得目标值，而不是转化为临时的Map对象后通过map.get("key")取得。
     * </pre>
     *
     * @param json     目标JSON字符串
     * @param nodeKeys 抵达目标节点所有的节点key或数组下标
     * @return 目标值
     */
    public static String getValue(String json, ObjectMapper om, String... nodeKeys) {
        try {
            JsonNode node = om.readTree(json);
            for (String nodeKey : nodeKeys) {
                if (node == null) {
                    return null;
                }
                if (NumberUtil.isInteger(nodeKey)) {
                    node = node.get(Integer.parseInt(nodeKey));
                } else {
                    node = node.get(nodeKey);
                }
            }
            return node.asText();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String getValue(InputStream inputStream, String... nodeKeys) {
        return getValue(inputStream, getDefaultObjectMapper(), nodeKeys);
    }

    public static String getValue(InputStream inputStream, ObjectMapper om, String... nodeKeys) {
        try {
            JsonNode node = om.readTree(inputStream);
            for (String nodeKey : nodeKeys) {
                if (node == null) {
                    return null;
                }
                if (NumberUtil.isInteger(nodeKey)) {
                    node = node.get(Integer.parseInt(nodeKey));
                } else {
                    node = node.get(nodeKey);
                }
            }
            return node.asText();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T getValue2Bean(String json, Class<T> valueType, String... nodeKeys) {
        return getValue2Bean(json, getDefaultObjectMapper(), valueType, nodeKeys);
    }

    public static <T> T getValue2Bean(String json, ObjectMapper om, Class<T> valueType, String... nodeKeys) {
        try {
            JsonNode node = om.readTree(json);
            for (String nodeKey : nodeKeys) {
                if (node == null) {
                    return null;
                }
                if (NumberUtil.isInteger(nodeKey)) {
                    node = node.get(Integer.parseInt(nodeKey));
                } else {
                    node = node.get(nodeKey);
                }
            }
            return om.convertValue(node, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T getValue2Bean(InputStream inputStream, Class<T> valueType, String... nodeKeys) {
        return getValue2Bean(inputStream, getDefaultObjectMapper(), valueType, nodeKeys);
    }

    public static <T> T getValue2Bean(InputStream inputStream, ObjectMapper om, Class<T> valueType,
                                      String... nodeKeys) {
        try {
            JsonNode node = om.readTree(inputStream);
            for (String nodeKey : nodeKeys) {
                if (node == null) {
                    return null;
                }
                if (NumberUtil.isInteger(nodeKey)) {
                    node = node.get(Integer.parseInt(nodeKey));
                } else {
                    node = node.get(nodeKey);
                }
            }
            return om.convertValue(node, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> List<T> toList(String jsonInString, Class<T> valueType, ObjectMapper om) {
        try {
            JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return om.readValue(jsonInString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> List<T> toList(InputStream json, Class<T> valueType) {
        return toList(json, valueType, getDefaultObjectMapper());
    }

    public static <T> List<T> toList(InputStream json, Class<T> valueType, ObjectMapper om) {
        try {
            JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return om.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> List<T> toList(String jsonInString, Class<T> valueType) {
        return toList(jsonInString, valueType, getDefaultObjectMapper());
    }

    public static <KEY, VALUE> List<Map<KEY, VALUE>> toListMap(String jsonInString) {
        return toListMap(jsonInString, getDefaultObjectMapper());
    }

    public static <KEY, VALUE> List<Map<KEY, VALUE>> toListMap(String jsonInString, ObjectMapper om) {

        try {
            return om.readValue(jsonInString, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Map<String, Object> toMap(String jsonInString) {
        return toMap(jsonInString, getDefaultObjectMapper());
    }

    public static Map<String, Object> toMap(String jsonInString, ObjectMapper om) {
        return toMap(jsonInString, String.class, Object.class, om);
    }

    public static <K, V> Map<K, V> toMap(String jsonInString, Class<K> keyClass, Class<V> valueClass) {
        return toMap(jsonInString, keyClass, valueClass, getDefaultObjectMapper());
    }

    public static <V> Map<String, V> toMap(String jsonInString, Class<V> valueType) {
        return toMap(jsonInString, String.class, valueType);
    }

    public static <K, V> Map<K, V> toMap(String jsonInString, Class<K> keyClass, Class<V> valueClass, ObjectMapper om) {
        try {
            MapType javaType = om.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
            return om.readValue(jsonInString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Map<String, Object> toMap(InputStream inputStream) {
        return toMap(inputStream, getDefaultObjectMapper());
    }

    public static Map<String, Object> toMap(InputStream inputStream, ObjectMapper om) {
        return toMap(inputStream, String.class, Object.class, om);
    }

    public static <V> Map<String, V> toMap(InputStream inputStream, Class<V> valueClass, ObjectMapper om) {
        return toMap(inputStream, String.class, valueClass, om);
    }

    public static <K, V> Map<K, V> toMap(InputStream jsonInString, Class<K> keyClass, Class<V> valueClass,
                                         ObjectMapper om) {
        try {
            MapType javaType = om.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
            return om.readValue(jsonInString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <V> Map<String, V> toMap(InputStream jsonInString, Class<V> valueClass) {
        return toMap(jsonInString, valueClass, getDefaultObjectMapper());
    }

    public static <T> T toObject(String jsonInString, Class<T> valueType) {
        return toObject(jsonInString, valueType, getDefaultObjectMapper());
    }

    public static <T> T toObject(String jsonInString, Class<T> valueType, ObjectMapper om) {
        try {
            return om.readValue(jsonInString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T toObject(InputStream inputStream, Class<T> valueType) {
        return toObject(inputStream, valueType, getDefaultObjectMapper());
    }

    public static <T> T toObject(InputStream inputStream, Class<T> valueType, ObjectMapper om) {
        try {
            return om.readValue(inputStream, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String toString(Object jsonOutObj) {
        return toString(jsonOutObj, getDefaultObjectMapper());
    }

    public static String toString(Object jsonOutObj, ObjectMapper om) {
        try {
            return om.writeValueAsString(jsonOutObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String writeValueAsString(Object jsonOutObj) {
        return writeValueAsString(jsonOutObj, getDefaultObjectMapper());
    }

    public static String writeValueAsString(Object jsonOutObj, ObjectMapper om) {
        try {
            return om.writeValueAsString(jsonOutObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static JsonNode readTree(String content) {
        try {
            return readTree(content, getDefaultObjectMapper());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T treeToValue(TreeNode node, Class<T> valueType) {
        try {
            return treeToValue(node, valueType, getDefaultObjectMapper());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T treeToValue(TreeNode n, Class<T> valueType, ObjectMapper om) {
        try {
            return om.treeToValue(n, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static JsonNode readTree(String content, ObjectMapper om) {
        try {
            return om.readTree(content);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static JsonNode readTree(byte[] content) {
        return readTree(content, getDefaultObjectMapper());
    }

    public static JsonNode readTree(byte[] content, ObjectMapper om) {
        try {
            return om.readTree(content);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T toObject(byte[] payload, Class<T> valueType) {
        return toObject(payload, valueType, defaultObjectMapper);
    }

    public static <T> T toObject(byte[] payload, Class<T> valueType, ObjectMapper om) {
        try {
            return om.readValue(payload, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
