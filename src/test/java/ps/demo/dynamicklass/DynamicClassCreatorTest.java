package ps.demo.dynamicklass;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.lang.GroovyClassLoader;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DynamicClassCreatorTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGroovyClasses() throws Exception {
        //groovy提供了一种将字符串文本代码直接转换成Java Class对象的功能
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        //里面的文本是Java代码,但是我们可以看到这是一个字符串我们可以直接生成对应的Class<?>对象,而不需要我们写一个.java文件
        Class<?> clazz = groovyClassLoader.parseClass(
                """
                        package ps.demo.dynamicklass.dto;
                                                
                        import java.util.Date;
                                                
                        public class Pojo2 {
                                                
                            private int id;
                            private String name;
                            private Double score;
                            private Date at;
                                                
                            public int getId() {
                                return id;
                            }
                                                
                            public void setId(int id) {
                                this.id = id;
                            }
                                                
                            public String getName() {
                                return name;
                            }
                                                
                            public void setName(String name) {
                                this.name = name;
                            }
                                                
                            public Double getScore() {
                                return score;
                            }
                                                
                            public void setScore(Double score) {
                                this.score = score;
                            }
                                                
                            public Date getAt() {
                                return at;
                            }
                                                
                            public void setAt(Date at) {
                                this.at = at;
                            }
                                                
                            @Override
                            public String toString() {
                                return "--Pojo2{" +
                                        "id=" + id +
                                        ", name='" + name + '\\'' +
                                        ", score=" + score +
                                        ", at=" + at +
                                        '}';
                            }
                                                
                            public String print() {
                                System.out.println(this);
                                return this.toString();
                            }
                            
                            public String validate() {
                                StringBuilder sb = new StringBuilder();
                                if (id == 0) {
                                    sb.append("id is invalid");
                                    sb.append("\\n");
                                }
                                if (name == null || name.trim().equals("")) {
                                    sb.append("name is invalid");
                                    sb.append("\\n");
                                }
                                if (at == null) {
                                    sb.append("at is invalid");
                                    sb.append("\\n");
                                }
                                return sb.toString();
                            }
                        
                        
                        }
                        
                                             
                                                
                        """

        );
        Object obj = clazz.newInstance();
        Method set1 = clazz.getDeclaredMethod("setAt", Date.class);
        set1.invoke(obj, new Date());

        Method method = clazz.getDeclaredMethod("print");
        Object result = method.invoke(obj);
        System.out.println("result = " + result);


        String json = objectMapper.writeValueAsString(obj);
        System.out.println("json = " + json);

        String jsonStr = """
                {"id":2,"name":"","score":"134.1","at":1711641828211}
                """;
        Object instance = objectMapper.readValue(jsonStr, clazz);
        Method validate = clazz.getDeclaredMethod("validate");
        String validationResult = validate.invoke(instance) + "";
        System.out.println("instance = " + instance);
        System.out.println("validation result = " + validationResult);

    }

}