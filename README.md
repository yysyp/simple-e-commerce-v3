## This is a sample Java project, It is using H2 memory mode DB, baomidou mybatis-plus and JPA. 
## So you should not need to pre-setup any DB or any config, just run "WebServerApplication.java".
## Below features are implemented by "baomidou mybatis-plus" and the table schema is in: src/main/resources/schema_h2.sql, which will be automatically initialized during springboot startup:
1. Simple e-commerce APIs like: Product, Cart, Order, Payment.
2. Mock API which allows you to create a Restful API mocker by calling the Restful API: '/mock/create-mock'
3. Excel download and upload: "/api/excel/download" and "/api/excel/upload"
## Below features are implemented by "Spring JPA" and no table schema is needed, during springboot starting it will create table base on the jakarta.persistence.Entity:
1. "mytftemplate/AbcStaff", and the URI: "/api/mytftemplate/abc-staff" is for accessing the CRUD operations from Jquery based page.
2. Currently the "pslab/MyCodeGenerator.java" automation tool generated feature is using "Spring JPA".


### This can be used as a project prototype for single Java Springboot project initialization.
### For multiple different modules, acting as quick check quick code snippet poc, you can use "sampleall".
### Rename & Replace Project Steps & Start Service:
1. Replace "simple-e-commerce-v3" with "new-proj-id"
2. Remove .mvn , mvnw , mvnw.cmd
3. Remove "<repositories>" in the pom.xml
4. Refactor "ps.demo" to "com.xx.yy.zz"
5. Replace "ps.demo" with "com.xx.yy.zz"
6. Start springboot: src/main/java/ps/demo/WebServerApplication.java


# simple-e-commerce-v3
This is a simple e-commerce demo based on springboot framework in Java 17
This application uses H2 memory database, and provide Restful APIs to show
a simple e-commerce scenarios including:
1. adding product to cart.
2. removing product from cart.
3. getting the cart detail.
4. placing an order.

### Build prerequisites:
- JDK: OpenJDK 17 installed
- Set JAVA_HOME, environment variables and add OpenJDK path to system environment variable, make sure java command is working

### Build steps:
1. Git download this repository and in folder "simple-e-commerce-v3" run command: ```mvnw clean package```
2. Run command to go to "target" folder: ```cd target```
3. start up application: ```java -jar simple-e-commerce-v3-1.0.0.jar```
4. Application started when you see "ps.demo.WebServerApplication - Started WebServerApplication" message.
5. Open your browser and navigate to the swagger doc page: http://localhost:8084/swagger-ui/index.html
6. On the swagger doc page, for each API you can click "Try it out" to call the API.
7. The seed data has been initiated in H2 database, so you can use below parameters: 
- userId: 1 to 3
- cartId: 1
- productId: 1 to 17

### Other URLs for reference: 
- Home index page: http://localhost:8084/
- API swagger doc: http://localhost:8084/swagger-ui/index.html
- OpenAPI in JSON format: http://localhost:8084/swagger-ui/api-docs
- OpenAPI in YAML format: http://localhost:8084/swagger-ui/api-docs.yaml
- H2 database console: http://localhost:8084/h2-console
- Sample CRUD: http://localhost:8084/api/mytftemplate/abc-staff
- 

### This application is based on memory H2 database, follow below steps to login to H2 database:
1. In browser open URL: http://localhost:8084/h2-console
2. Saved Settings set to: Generic H2 (Embedded)
3. Driver Class set to: org.h2.Driver
4. JDBC URL set to: jdbc:h2:mem:ecommercedb
5. User Name set to: sa
6. No password so, "Password" leave it empty.
7. Click "Connect" button.
8. You will be able to see the tables in left, and you can run SQL on the right panel.
9. And also there is an additional H2 DB, and the connection info is:
    Snd Data Source:
    Driver Class: org.h2.Driver
    JDBC URL: jdbc:h2:mem:snd;MODE=MYSQL
    User Name: sa
    Password:

### Start with docker:
- docker build -t app:v1 -f Dockerfile .
- docker run --name app --rm -itd -p:8084:8084 app:v1
- docker build -t app:v1 -f script/Dockerfile .
- docker stop app

### Restful Mocker:
#Mock usage:
1): http://localhost:8084/mock/create-mock to create mock.
2): http://localhost:8084/mock/api/{YourMockUri} to use the created mock.
On the "create-mock" phase, the headers and body content will be parsed by thymeleaf engine.
and the "request" variable will be passed to the script context. So basically you can put the
content as below: {"requestMethod": "[(${request.method})]", "randomStr": "[(${#strings.randomAlphanumeric(8)})]"}
Example to create mock:
curl -X 'POST' \
'http://localhost:8084/mock/create-mock' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"uri": "myrest-api",
"regexMatch": true,
"method": "get",
"status": 200,
"headers": "{\"myresponse-header-str\": \"[(${#strings.randomAlphanumeric(8)})]\"}",
"body": "{\"requestMethod\": \"[(${request.method})]-[(${env.TMP})]\", \"randomStr\": \"[(${#strings.randomAlphanumeric(8)})]-[(${#strings.arrayJoin(#numbers.sequence(1, 5), '\'','\'')})]\"}"
}'

And to use the mock:
curl --location --request GET 'http://localhost:8084/mock/api/myrest-api'

### "Thymeleaf-Controller-Service-JPA Repository" Code Generator:
#Code generator usage:
This code generator is using thymeleaf template script to generate code.
This code generator will keep track the generated code files' MD5, if generated file
has not changed, then if you regenerate, the new generated files will overwrite the old ones.
The templates config folder is: tfsource
And steps as below:
1) Config the "entityJson" property in: tfsource/tf.properties
2) Config the "packageName" and "moduleName"
3) Run the "src/main/java/pslab/MyCodeGenerator.java" code will be generated.

### Code Generator Template Maker:
#Code generator templates tool:
This tool is for quick updating the thymeleaf template files for above "#Code generator"
This is performing the replaces for all .tf template files in "tfsource/"
The replaces is defined in "tfsource/tmlfTfmakeReplacement.properties"
Steps:
1) Update the code in: "src/main/java/ps/demo/mytftemplate" AND "src/main/resources/templates/mytftemplate"
2) Copy the code content to related template in "tfsource/".
3) Run the "src/main/java/pslab/MyTfTemplateGeneratorForCodeGenerator.java" to update the thymeleaf template files.

