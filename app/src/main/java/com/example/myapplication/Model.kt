package com.example.myapplication

public class Model{
    lateinit var ticker: String
    lateinit var changes: String
    lateinit var price: String
    lateinit var changesPercentage: String
    lateinit var companyName : String

    constructor(ticker: String, changes: String, price: String, changesPercentage: String, companyName: String){
        this.ticker = ticker
        this.changes = changes
        this.price = price
        this.changesPercentage = changesPercentage
        this.companyName = companyName
    }

    constructor()
}