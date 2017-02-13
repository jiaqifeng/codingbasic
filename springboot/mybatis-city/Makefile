all:
	mvn spring-boot:run

curl:
	curl http://localhost:8080/api/city?cityName=shenyang

msgtest:
	curl -H "Content-Type: application/json" -X POST  --data '{"data":"1"}' http://localhost:8080/msg
	curl -H "Content-Type: application/json" -X POST  --data 'jackey' http://localhost:8080/msg
	curl http://localhost:8080/msg?id=1
	curl http://localhost:8080/msg?id=2
