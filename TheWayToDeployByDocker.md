## build

- chmod +x ./graldew
- ./gradlew clean
- ./gradlew build -x test

### docker image 만들기

- docker build -t [이미지명] .
- ex) docker build -t yjw-boot-image .

### docker 실행
> 바인딩시 플젝 기준에서 실행해야 uploads 디렉토리 바인딩됨

- docker run -d -p 30000:30000 --name [컨테이너명] [이미지명]
- ex) docker run -d -p 30000:30000 --network chatting-network --name yjw-container yjw-boot-image
- [upload바인딩]ex) docker run -d -v $(pwd)/uploads:/app/uploads -p 30000:30000 --network chatting-network --name yjw-container yjw-boot-image

### mariadb ip가 도커 내부 Ip인 이유
- 포트포워딩을 하더라도 spring에서 도메인으로 연결시 db 연결이 안됨
- chatting-network 도커 네트워크 생성 후 내부로 DB 연결처리함
   > mariadb(172.19.0.2), springboot



--- 
### 삭제 후 재배포
1. docker stop yjw-container
2. docker rm yjw-container
3. docker rmi yjw-boot-image
4. ./gradlew clean
5. ./graldew build -x test
6. docker build -t yjw-boot-image .
7. docker run -d -p 30000:30000 --network chatting-network --name yjw-container
