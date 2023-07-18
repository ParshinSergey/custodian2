Project info:

* Ссылка на Докер: https://hub.docker.com/repository/docker/parshinsergey/custodian/general
* Запустить проект одним из 2х способов (у меня на Windows из командной строки сработал только 1ый):  
    docker run -p 8080:8080 -v ‪%cd%:/testDirectory parshinsergey/custodian:1.0.2  
    docker run -p 8080:8080 -v ‪${PWD}:/testDirectory parshinsergey/custodian:1.0.2
    
В Postman можно вызвать метод GET http://localhost:8080/api/request/balance
с телом JSON  
{
    "header":
        {
        "requestType": "Balance",
        "requestID": "B530EEAD-9582-4728-80F8-A1F888AF87E2",
        "sourceAPPidentity": "1DD4EC32-45DB-404A-A123-6F657895E502",
        "endUserID": "70DA40D1-5944-4406-9827-30AA5E3ACD91"
        },
    "body":
        {
        "balance":{
            "account": "400820-UA40000001"
            }
        }
}

В текущую рабочую директорию будут записываться файлы вида request*********.xml

Еще можно явно создать директорию, например ‪C:\myDirectory и тогда запускать  
docker run -p 8080:8080 -v ‪C:\myDirectory:/testDirectory parshinsergey/custodian:1.0.2  
и файлы будут там.







