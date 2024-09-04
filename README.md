# 한 발 앞선 생각 - Don Touch

<br/>

> 신한투자증권 프로 디지털 아카데미 3기 최종 프로젝트
> <br/><br/>
> 주제: 개인화된 금융 서비스를 제공하기 위한 클라우드 MSA 환경 구축 및 서비스 개발
> <br/><br/>
> 최우수 프로젝트 수상

<br/>
<br/>


<br/>

# 서비스 소개
<br />
금융투자협회 등 공신력 있는 기관의 통계에 따르면 주식 거래 활동 계좌 수 및 투자자 예탁금이 계속해서 증가하는 추세를 보이고 있습니다.
그 중 노후 준비를 위한 투자의 비중이 늘어나고 있고, 중/장년층은 정기적인 소득을 얻을 수 있는 '배당주 투자'에 관심을 두고 있습니다. 
50대 이상 투자자들이 개인 투자자들에게 지급된 배당금의 74% 이상을 받아가는 등, 배당주에 대한 수요는 폭발적으로 증가하는 중임을 볼 수 있습니다.

하지만, 이렇게 증가하는 수요에 대비해서 배당주 투자와 관련된 정보는 많이 없고 관련된 서비스도 부족합니다.
대부분의 정보는 보편적인 월배당 포트폴리오의 공유에 그치고, 개인의 재정상황과 투자 성향등을 고려하지 못합니다.
소수의 증권사들에서 배당주 관련 서비스가 출시되어 있기는 하나, 치명적인 단점들을 여럿 가지고 있습니다.

* 월당 1개의 종목만 고를 수 있고,
* 종목과 구매 주수를 직접 결정해야 하고,
* 예상 배당금을 볼 수 없으며,
* **개인화된 추천을 제공하지 못한다**는 단점을 가지고 있습니다.

<br />

그래서 저희는 이러한 문제점들을 해결하기 위한 서비스를 기획하였습니다.

* 투자성향과 보유한 주식 등을 통해 사용자 성향에 맞는 종목으로 **개인화된** 조합을 구성하고,
* 예상 배당금을 미리 확인할 수 있게 하여 일정한 월 소득을 설계하고,
* 소액 투자 및 포트폴리오를 분산할 수 있는 대안을 제시하고자 하였습니다.

<br />
<br />

# Project Architecture 
![image](https://github.com/PDA-Dontouch/.github/assets/128025654/4f1d5e14-5bc1-40e8-ad3a-2c17bbd851b0)
<br />
<br />

### MSA 구조
* 요구 사항인 MSA 구조를 통한 금융 서비스 구현을 위하여 6개의 서버 및 DB 분리
* 도메인 별 5개의 서버(Spring boot)와 5개의 데이터베이스(Maria DB) - 유저, 주식, 부동산p2p, 에너지p2p, 오더 서버
* 실시간 데이터 통신을 위한 소켓 서버(NodeJs)와 체결 및 거래 DB(Mongo DB). 이벤트 핸들링 최적화
* 비동기 통신을 위한 Kafka 서버 - kafka 2.8.0, Zookeeper 등
### 서버 라우팅 & 로드 밸런싱
* ELB : 서버의 탄력적인 확장성과 안정적인 운영을 위하여  **서로 다른 AZ의 서버로 로드밸런싱**. *트래픽 분산*
* PM2 : **클러스터 모드**를 활용하여 **서버 클러스터링** (scale out 전략)
* NginX : 로드밸런싱 configuration 을 활용하여 **클러스터링 된 서버 로드 밸런싱**. **클라이언트 - 서버,** **서버 - 서버** 라우팅
### 서버 간 결합도를 줄이기 위한 유저 정보 반정규화 및 Spring Security 를 활용한 인증/인가
### 서버 간 신뢰성이 보장되어야 하는 로직 -> HTTP 통신
### 서버 간 인증이 완료되고, 단방향으로 진행되는 로직 -> Kafka 활용 메세지 큐잉
### 실시간 매매 구현을 위한 socket 서버. 이벤트 핸들링 최적화 -> nodeJs 활용


# Tech Stacks

# ERD

MSA 환경 구축을 위해 도메인 별로 DB를 나누어 구축하였습니다.

<img width="595" alt="user" src="https://github.com/PDA-Dontouch/.github/assets/128025654/78e08049-a483-4b75-8fb5-ae8fc40e2beb">
<img width="821" alt="stock" src="https://github.com/PDA-Dontouch/.github/assets/128025654/d3378834-aabb-45d2-93ef-d5ef7c8f067c">
<img width="526" alt="order" src="https://github.com/PDA-Dontouch/.github/assets/128025654/942bcd2f-8e37-4711-9dc3-c20b39dc8e5f">
<img width="553" alt="estate" src="https://github.com/PDA-Dontouch/.github/assets/128025654/f603ca4a-3aec-49be-a028-b57d5419f646">
<img width="457" alt="energy" src="https://github.com/PDA-Dontouch/.github/assets/128025654/90de9cd7-3e52-436b-aaf7-3d2df4ffab4f">

# Algorithm
기업의 가치를 평가하기 위해 여러개의 재무제표를 활용하였습니다.
'안정', '성장', '배당' 3개의 영역으로 나누어, 아래와 같은 지표를 통해 종목 평가를 진행하였습니다.
![image](https://github.com/PDA-Dontouch/.github/assets/128025654/d1952695-39ef-4b44-b2ac-0dc1e92f66ae)
<br />
각각의 영역에 대한 점수를 매긴 후, Z점수 [(자료 값-평균)/표준편차] 를 활용하여 표준화하였습니다.

이렇게 계산된 종목의 평가 점수를 사용자의 투자 성향 및 보유한 주식에 따라 개인화 점수로 다시 계산합니다.
이후 사용자에게 최적이며, 매달 정기적인 배당을 받을 수 있는 조합을 추천해 줍니다. 


# Main Features

### 배당주 조합 추천 및 구매

사용자의 투자성향 및 투자 금액을 수집 후, 개인화된 배당주 조합을 추천해줍니다.
조합에 더 추가하고 싶은 종목이 있다거나 마음에 들지 않는 종목이 있다면 자유롭게 커스터마이징이 가능합니다. 
매 달의 배당주 조합을 고르고 나면 최종 조합 결과가 출력되며, 최대한 균등하게 배당을 수령할 수 있도록 배당금의 금액 맞춰 종목 수를 자동적으로 조절해줍니다.

### 개별 종목 추가 구매

조합 추천 이외에 추가적으로 개별 구매하고자 하는 종목이 있다면 검색을 통해 구매할 수 있습니다.
개별 종목의 상세 페이지에서는 실시간 가격과 원하는 기간(장/단기) 차트 및 재무제표를 보여주며, 사용자가 종목의 구매 여부를 판단하기 위한 많은 근거를 제공합니다.

### P2P 금융 상품 구매

소액 투자자 및 배당주보다 높은 수익을 원하는 투자자들을 위한 투자 상품 또한 구매할 수 있습니다.
사용자의 성향에 따라 수익률 혹은 안정성 순서대로 정렬하여 종목을 보여주며, 실제 규제에 따라 한 종목은 500만원 / 모든 P2P 상품은 합쳐서 4000만원까지 투자할 수 있도록 하였습니다.

### 메인 페이지 및 캘린더를 통한 배당 일정 확인

메인 페이지에서는 사용자의 투자성향, 이번달의 배당금과 배당 일정 및 총 자산을 볼 수 있습니다.

캘린더 페이지에서는 한달간의 배당 일정을 모두 볼 수 있으며 메인 페이지에서는 이번주의 일정으로 간소화하여 나타내었습니다.
배당금이 지급될 예정인 날짜와 종목명, 배당금을 한눈에 확인할 수 있도록 하여 사용자가 다양한 상품을 투자할 때 느꼈던 배당 일정 관리의 불편함을 해소시켰습니다.

관심종목 보기와 투자성향 다시 테스트하기 기능도 포함하여 최적의 개인화를 제공할 수 있도록 하였습니다.

<br />


# Project Structure


Backend:
```

├─energy-server
│      └─src
│      ├─main
│      │  ├─java
│      │  │  └─donTouch
│      │  │      └─energy_server
│      │  │          ├─energy
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─kafka
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          └─utils
│      │  └─resources
│      └─test
│          └─java
│              └─donTouch
│                  └─energy_server
├─estate-server
│      └─src
│      ├─main
│      │  ├─java
│      │  │  └─donTouch
│      │  │      └─estate_server
│      │  │          ├─estate
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  ├─service
│      │  │          │  └─utils
│      │  │          ├─kafka
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          └─utils
│      │  └─resources
│      └─test
│          └─java
│              └─donTouch
│                  └─estate_server
├─order-server
│      └─src
│      ├─main
│      │  ├─java
│      │  │  └─donTouch
│      │  │      └─order_server
│      │  │          ├─bankAccount
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─holding
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─kafka
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─log
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          └─utils
│      │  └─resources
│      └─test
│          └─java
│              └─donTouch
│                  └─order_server
├─src
│  ├─main
│  │  └─java
│  │      └─donTouch
│  │          └─backend_server
│  └─test
│      └─java
│          └─donTouch
│              └─backend_server
├─stock-server
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─donTouch
│      │  │      └─stock_server
│      │  │          ├─dividend
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─kafka
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─krStock
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─stock
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─usStock
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          └─web
│      │  │              └─dto
│      │  └─resources
│      └─test
│          └─java
│              └─donTouch
│                  └─stock_server
├─user-server
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─donTouch
│      │  │      └─user_server
│      │  │          ├─kafka
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─like
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─oauth
│      │  │          │  ├─config
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─user
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  ├─service
│      │  │          │  └─utils
│      │  │          ├─userEnergy
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          ├─userEstate
│      │  │          │  ├─domain
│      │  │          │  ├─dto
│      │  │          │  └─service
│      │  │          └─userStock
│      │  │              ├─domain
│      │  │              ├─dto
│      │  │              └─service
│      │  └─resources
│      └─test
│          └─java
│              └─donTouch
│                  └─user_server

```
