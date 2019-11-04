package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", getIndex)
	http.HandleFunc("/hello", getHello)
	http.ListenAndServe(":8888", nil)
}

func getIndex(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "This is a golang server!")
}

func getHello(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "hello Golang!")
}