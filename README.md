Simple currency info.

1.To get data from NBU and save it to DB use "/getdata"

2.To read selected currency data from DB use "/currency/{currency}, where {currency} is one of the selected from list below:
    EUR - Euro
    RUB - Russian rubles
    AUD - Australian dollar
    USD - US Dollar
    UAH - Ukranian hryvnya
    and so on...
  If data wolud not be found in DB, service will try to get it from NBU.

3.To read data from DB by date use "/date/{date}, where {date} is date in format DDMMYYYY, for example 22012000.
  If data wolud not be found in DB, service will try to get it from NBU.

4.To read data from DB by date and selected currency use /currency&date/{currency}&{date}, where currency is selected one and date in format DDMMYYYY.
  If data wolud not be found in DB, service will try to get it from NBU.
