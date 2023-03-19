# altice_2023_challenge

1. Make sure you have quarkus installed
2. Inside the folder altice-desafio-2023, open your terminal and run docker-compose up -d --build
3. The application is now running in localhost:8080
4. For test the app you can go to http://localhost:8080/swagger and try the endpoint 
5. You can also use Insomnia or Postman to test the endpoint
    -  http://localhost:8080/labseq/{n} where you give n the value you want, if the value provided is not a positive integer it will raise an exception
