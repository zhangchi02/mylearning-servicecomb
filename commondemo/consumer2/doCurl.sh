#! /bin/bash
a=0
while [ $a -le 10 ]; do
	a=$((a+1))
	curl -k -H 'Content-Type:application/json;charset=utf-8' "http://192.168.0.125:7071/consumer/v0/hello?name=delay"

done
