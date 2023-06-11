# **Kiosk Project**
***
> **project list**
> * **grpcInterface**  
>   Protocol Buffers 를 통해 통신하기 위한 proto 파일들이 정의되어 있다.
> * **grpcServer**  
>   springboot를 통해 Protocol Buffers 를 사용할 수 있도록 LogNet Library 롤 포함하고 있다. 
>

## **grpcInterface**
***
> Build
> ```
> ./gradlew :grpcInterface:build
> ```

## **grpcServer**
***
### **grpcServer 실행 명령어**
> * Clean  
>   기존에 build 되어 생성되었던 jar, classes 들을 삭제 한다. (예외 디렉토리 : out, logs)
>   ```
>   ./gradlew :grpcServer:clean
>   ```
> * Build  
>   서브 프로젝트인 grpcServer를 빌드 한다.
>   ```
>   ./gradlew :grpcServer:build
>   ```
> * Test  
>   grpcServer 의 Unit 테스트를 진행한다.
>   포함되어 있는 내용은 다음과 같아
>   * Unit Test
>   * Jacoco Test
>   * Jacoco Report 생성  
>     생성위치 : ``grpcServer/build/reports/jacoco/test/html/index.html``
>   ```
>   ./gradlew :grpcServer:test
>   ```
> * BootRun  
>   grpcServer 프로젝트를 실행한다.
>   ```
>   ./gradlew :grpcServer:bootRun
>   ```
### **grpcTest 방법**
> ``grpcurl``을 이용한 테스트  
> > * ``grpcurl`` 설치  
> >   [https://github.com/fullstorydev/grpcurl](https://github.com/fullstorydev/grpcurl)    
> >   
> > 
> > * 서비스 목록 보기 (grpc.enableReflection=true 상태에서 가능)
> >   ```bash
> >   grpcurl --plaintext localhost:9091 list
> >   
> >   grpcurl --plaintext localhost:9091 list net.monki.kiosk.Simple
> >   ```  
> > * 서비스 호출하기 (``net.monki.kiosk.Simple``)
> >   ```bash
> >   grpcurl -plaintext localhost:9091 net.monki.kiosk.Simple.SayHello
> >   # or
> >   grpcurl -plaintext localhost:9091 net.monki.kiosk.Simple/SayHello
> >   # or 
> >   grpcurl -plaintext -d '{ "name": "SanMaRu"}' localhost:9091 net.monki.kiosk.Simple/SayHello
> >   ```
> ``postman``을 이용한 테스트  
> 

