#! /bin/bash
x="a b c d e f g h i j"
for i in $x ;do
    echo $i
	y=0
    while [ $y -le 49 ] ;do
	    y=$((y+1))
	    curl -v -H 'Content-Type:application/json;charset=utf-8' "http://192.168.0.125:8080/provider/v0/hello$i$y?name=Alice"
    done
done
