package ps.demo.common;

import com.alibaba.testable.core.annotation.MockDiagnose;
import com.alibaba.testable.core.annotation.MockInvoke;
import com.alibaba.testable.core.annotation.MockWith;
import com.alibaba.testable.core.model.LogLevel;
import com.alibaba.testable.core.tool.PrivateAccessor;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This is the TestableMock example
 */
@EnablePrivateAccess(srcClass = YamlTool.class)
@MockDiagnose(LogLevel.VERBOSE)
class YamlToolTest {

    //YamlTool yamlTool = new YamlTool();
    @Test
    void writeObjectAsString() {
        Object obj = new Object();

        PrivateAccessor.setStatic(YamlTool.class, "mapper", new ObjectMapper());

        String str = YamlTool.writeObjectAsString(obj);
        Assertions.assertEquals("This is from mock", str);
    }


    public static class Mock {

        @MockInvoke(targetClass = ObjectMapper.class, targetMethod = "writeValueAsString")
        private String writeValueAsStringMock(Object par) {
            System.out.println("--->>created mock for object.writeValueAsString");
            return "This is from mock";
        }

    }

}