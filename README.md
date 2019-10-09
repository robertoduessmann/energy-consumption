# energy-consumption

[![Build Status](https://travis-ci.org/robertoduessmann/energy-consumption.svg?branch=master)](https://travis-ci.org/robertoduessmann/energy-consumption)

> Collect and report energy consumption
 
## Technologies
- Java 11
- Spring Boot
- Openfeign
- H2 Database

## Running in a container

Build image from a Dockerfile:
```console
$ docker build -t energy-consumption:latest .
```
Run docker image:
```console
$ docker run -p 8080:8080 energy-consumption:latest
```

## Usage

### POST /counter_callback
> Send data about energy consumption from different villages
```console
$ curl -X POST \
    https://energyconsumptionreport.herokuapp.com/counter_callback \
    -H 'content-type: application/json' \
    -d '{
        "counter_id": "1",
        "amount": 10000.123
    }'
```

### GET /consumption_report?duration={duration}
> Get the consumption report per village for the last n hours
```console
$ curl -X GET https://energyconsumptionreport.herokuapp.com/consumption_report?duration=24h
```
```json
{
    "villages": [
        {
            "village_name": "Villarriba",
            "consumption": 12345.123
        },
        {
            "village_name": "Villabajo",
            "consumption": 23456.123
        }
    ]
}
```
