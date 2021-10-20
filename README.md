# Coronavirus Tracker (Spring Boot)
![build](https://github.com/mushipeas/coronavirustracker/actions/workflows/maven.yml/badge.svg)

A SpringBoot application which displays daily covid case rates, split across regions, as well as calculated total and daily case-rates.

The data is loaded daily from:
 https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv

A demo can be seen at:
https://coronavirustracker-springboot.herokuapp.com/


## Recommended Setup for Development:

Open the project with vs-code and activate the dev-container.

Tests currently make use of Mockito and MockMVC modules.

## TODO:

- Add tests for Thymeleaf templates
- Test webpage using Selenium
