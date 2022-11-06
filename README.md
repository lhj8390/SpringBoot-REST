## spring-boot-react-app
spring boot와 react를 사용해서 간단한 대시보드를 구현했습니다.

### 기술 스택
Component         | Technology
---               | ---
Frontend          | [React.js](https://github.com/facebook/create-react-app)
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
Security          | Token Based (Spring Security and [JWT](https://github.com/auth0/java-jwt) )
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox)
In Memory DB      | H2 
Persistence       | JPA (Using Spring Data)
Test              | Junit, Mockito
Client Build Tools| Webpack, npm
Server Build Tools| Gradle

### 파일 구조
```bash
PROJECT_FOLDER
│  README.md         
│  build.gradle
└──[src]      
│  └──[main]      
│     └──[java]         # 백엔드 관련 소스파일
│     └──[resources]
│        │  application.yml  # 스프링 부트 구성 포함
│        │  data.sql    # 앱 시작 중에 Mock 데이터를 삽입하기 위한 DB 스크립트
│        └──[static]    # 리소스 디렉터리 (이미지)
│
└──[dashboard-ui]
   │  package.json     
   └──[src]              # 프론트 관련 소스파일
```

### 전제 조건
- Java 8
- Gradle 7.5+
- Node 16.14.0
- npm 8.3.1

## Getting Started
소스코드를 로컬에 복제합니다.
```bash
git clone https://github.com/lhj8390/spring-boot-react-app.git dashboard
cd dashboard
```
서버를 실행합니다.
```bash
gradle build
cd build/libs/ && java -jar dashboard-0.0.1-SNAPSHOT.jar
```

클라이언트를 실행하려면 `dashboard-ui` 폴더로 이동하여 다음을 실행합니다.
```bash
npm i && npm run start
```

#### 앱 URL
Component         | URL                                              | Credentials
---               | ---                                              | ---
Frontend          |  http://localhost:2000                           | 
H2 Database       |  http://localhost:8080/h2-console                |  Driver:`org.h2.Driver` <br/> JDBC URL:`jdbc:h2:file:./test` <br/> User Name:`sa`
Swagger           |  http://localhost:8080/swagger-ui/index.html#/   | 

#### 테스트 계정
- ID : `test@test.com`
- password : `test1234`

로그인 화면의 `test 계정` 버튼을 클릭하면 자동으로 입력됩니다.
