package ps.demo.protobuf;

import com.google.protobuf.util.JsonFormat;
import lombok.SneakyThrows;
import ps.demo.protobuf.model.Course;
import ps.demo.protobuf.model.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseTest {

    @SneakyThrows
    public static void main(String[] args) {
        Course course1 = Course.newBuilder()
                .setId(1)
                .setCourseName("REST with Spring")
                .addAllStudent(createTestStudents())
                .build();

        System.out.println("course1 = " + course1);
        System.out.println("byte[] = "+course1.toByteArray());

//        Builder structBuilder = Struct.newBuilder();
//        JsonFormat.parser().ignoringUnknownFields().merge(json, structBuilder);
//        Message message = structBuilder.build();

        //object to json
        String str = JsonFormat.printer().print(course1);
        System.out.println("str =  " + str);
        //json to object
        Course.Builder builder = Course.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(str, builder);
        System.out.println("obj = " + builder.build());

    }

    public static List<Student> createTestStudents() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = Student.newBuilder().setId(i).setFirstName("firstname" + i)
                    .setLastName("lastname" + i).setEmail("email" + i + "@a.com")
                    .build();

//            .setPhone(0, Student.PhoneNumber.newBuilder().setType(Student.PhoneType.MOBILE)
//                    .setNumber("13421541251").build())
            students.add(student);
        }
        return students;
    }
}
